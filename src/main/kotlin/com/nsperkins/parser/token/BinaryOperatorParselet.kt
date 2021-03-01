package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.Expression
import com.nsperkins.parser.ast.OperatorExpression

class BinaryOperatorParselet : InfixParslet {
    override fun parse(
        parser: Parser,
        left: Expression,
        token: Token
    ): Expression {
        val right: Expression = parser.parse()
        return OperatorExpression(left, token, right)
    }
}