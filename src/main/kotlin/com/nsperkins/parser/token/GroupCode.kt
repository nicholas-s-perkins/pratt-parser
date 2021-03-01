package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.AstNode

class GroupCode : NudCode {
    override fun parse(parser: Parser, token: Token): AstNode {
        val expression: AstNode = parser.parse()
        parser.next(Token.CloseParen::class)
        return expression
    }
}