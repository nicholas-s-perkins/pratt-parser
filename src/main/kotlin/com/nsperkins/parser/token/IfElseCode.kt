package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.AstNode
import com.nsperkins.parser.ast.IfElseNode

class IfElseCode : NudCode {
    override fun parse(parser: Parser, token: Token): AstNode {
        val ifCondition: AstNode = parser.parse(token.bindingPower)

        val thenToken = parser.next(Token.Then::class)
        val thenExpression = parser.parse(thenToken.bindingPower)

        val elseToken = parser.next(Token.Else::class)
        val elseExpression = parser.parse(elseToken.bindingPower)

        return IfElseNode(token, ifCondition, thenExpression, elseExpression)
    }
}