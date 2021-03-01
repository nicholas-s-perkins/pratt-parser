package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.Expression
import com.nsperkins.parser.ast.OperatorExpression


class BinaryOperatorParselet(
    override val precedence: Int,
    val associativity: Associativity = Associativity.LEFT
) : InfixParslet {
    override fun parse(
        parser: Parser,
        left: Expression,
        token: Token
    ): Expression {
        val right: Expression = parser.parse(precedence - (if (associativity == Associativity.RIGHT) 1 else 0))

        return OperatorExpression(left, token, right)
    }
}