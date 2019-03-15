package app.mediabrainz.api.search

import java.util.*


class LuceneBuilder {

    private val terms = ArrayList<LuceneTerm>()

    // TODO: add exceptions?
    fun add(operator: LuceneOperator): LuceneBuilder {
        val size = terms.size
        // 0: or param / and param
        if (size == 0 && operator != LuceneOperator.NOT) {
            //exception
            return this
        }
        if (size > 0) {
            val lastTerm = terms.last()
            // or or / and and / not not
            // or and / and or
            // not and / not or
            if (lastTerm.isOperator && (lastTerm.operator == operator || operator != LuceneOperator.NOT)) {
                //exception
                return this
            }
            // param not param >> param and not param
            if (operator == LuceneOperator.NOT && !lastTerm.isOperator) {
                terms.add(LuceneTerm.and())
            }
        }
        terms.add(LuceneTerm(operator))
        return this
    }

    fun add(paramType: SearchFieldInterface, value: String): LuceneBuilder {
        val size = terms.size
        // param param -> param and param
        if (size > 0 && !terms.last().isOperator) {
            terms.add(LuceneTerm.and())
        }
        terms.add(LuceneTerm(paramType, value))
        return this
    }

    fun add(value: String): LuceneBuilder {
        if (terms.isEmpty()) {
            terms.add(LuceneTerm(null, value))
        }
        return this
    }

    /**
     * @return String : "artist:fred AND type:group AND country:US" or "fred AND type:group AND country:US"
     */
    fun build(): String {
        val sb = StringBuilder(" ")
        if (!terms.isEmpty() && !terms[0].isOperator && terms[0].paramType == null) {
            sb.append(terms.removeAt(0).value).append(" ")
        }
        for (term in terms) {
            if (term.isOperator) {
                sb.append(term.operator)
            } else {
                sb.append(term.paramType).append(":").append(term.value)
            }
            sb.append(" ")
        }
        return sb.toString().trim { it <= ' ' }
    }

    fun clear() {
        terms.clear()
    }

}

enum class LuceneOperator(val operator: String) {
    AND("AND"),
    OR("OR"),
    NOT("NOT");

    override fun toString() = operator
}

private class LuceneTerm(val operator: LuceneOperator?) {

    var isOperator = false
    var paramType: SearchFieldInterface? = null
    var value: String? = null

    init {
        isOperator = operator != null
    }

    constructor(paramType: SearchFieldInterface?, value: String) : this(null) {
        this.paramType = paramType
        this.value = value
    }

    companion object {
        fun and() = LuceneTerm(LuceneOperator.AND)
        fun or() = LuceneTerm(LuceneOperator.OR)
        fun not() = LuceneTerm(LuceneOperator.NOT)
    }
}

