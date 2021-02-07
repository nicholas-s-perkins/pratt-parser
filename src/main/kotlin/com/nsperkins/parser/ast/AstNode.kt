package com.nsperkins.parser.ast

import com.nsperkins.parser.token.Token

class AstNode(
    val token: Token
) {
    var left: AstNode? = null
    var right: AstNode? = null

    /**
     * Basic tree print function from https://stackoverflow.com/a/8948691
     */
    override fun toString(): String {
        val buffer = StringBuilder(50)
        print(buffer, "", "")
        return buffer.toString()
    }

    private fun print(buffer: StringBuilder, prefix: String, childrenPrefix: String) {
        buffer.append(prefix)
        buffer.append(token.value)
        buffer.append('\n')

        //fix this
        val children = listOfNotNull(left, right).iterator()

        while (children.hasNext()) {
            val next = children.next()
            if (children.hasNext()) {
                next.print(buffer, "$childrenPrefix├── ", "$childrenPrefix│   ")
            } else {
                next.print(buffer, "$childrenPrefix└── ", "$childrenPrefix    ")
            }
        }
    }
}
