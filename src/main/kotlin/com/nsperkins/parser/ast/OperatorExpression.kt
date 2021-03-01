package com.nsperkins.parser.ast

import com.nsperkins.parser.token.Token

class OperatorExpression(
    val left: Expression,
    val operator: Token,
    val right: Expression
) : Expression {
    override fun print(builder: StringBuilder) {
        builder.append("(")
        left.print(builder)
        builder.append(" ").append(operator.value).append(" ")
        right.print(builder)
        builder.append(")")
    }

}