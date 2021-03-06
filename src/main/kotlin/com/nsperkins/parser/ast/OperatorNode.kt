package com.nsperkins.parser.ast

import com.nsperkins.parser.token.Token


class OperatorNode(
    val left: AstNode,
    val operator: Token,
    val right: AstNode
) : AstNode {
    override fun printExpression(builder: StringBuilder) {
        builder.append("(")
        left.printExpression(builder)
        builder.append(" ").append(operator.value).append(" ")
        right.printExpression(builder)
        builder.append(")")
    }

    override fun getToken() = operator
    override fun getChildren() = listOf(left, right)
}