package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.Expression
import com.nsperkins.parser.ast.PrefixExpression

class PrefixOperatorParselet : PrefixParselet {
    override fun parse(parser: Parser, token: Token): Expression {
        val operand: Expression = parser.parse()
        return PrefixExpression(token, operand)
    }
}
