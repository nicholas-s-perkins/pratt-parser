package com.nsperkins.parser.ast

import com.nsperkins.parser.token.Token


class OperatorNode(
    val left: AstNode,
    val operator: Token,
    val right: AstNode
) : AstNode {
    override fun print(builder: StringBuilder) {
        builder.append("(")
        left.print(builder)
        builder.append(" ").append(operator.value).append(" ")
        right.print(builder)
        builder.append(")")
    }

    override fun getToken() = operator
    override fun getChildren() = listOf(left, right)
}