package com.nsperkins.parser.ast

import com.nsperkins.parser.token.Token

/**
 * A null denotation expression or "prefix" expression, such as the minus in `-1`
 */
class PrefixNode(val operator: Token, val right: AstNode) : AstNode {
    override fun printExpression(builder: StringBuilder) {
        builder.append("(").append(operator.value)
        right.printExpression(builder)
        builder.append(")")
    }

    override fun getToken() = operator
    override fun getChildren() = listOf(right)
}