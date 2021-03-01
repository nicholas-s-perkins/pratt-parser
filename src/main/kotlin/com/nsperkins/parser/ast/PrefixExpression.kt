package com.nsperkins.parser.ast

import com.nsperkins.parser.token.Token

class PrefixExpression(val operator: Token, val right: Expression) : Expression {
    override fun print(builder: StringBuilder) {
        builder.append("(").append(operator.value)
        right.print(builder)
        builder.append(")")
    }
}