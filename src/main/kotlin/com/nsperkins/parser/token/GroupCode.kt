package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.AstNode
import com.nsperkins.parser.ast.GroupNode
import com.nsperkins.parser.ast.NameNode

class GroupCode : NudCode {
    override fun parse(parser: Parser, token: Token): AstNode {
        val contents: AstNode = parser.parse(token.bindingPower)
        val closeParen = parser.next(Token.CloseParen::class)
        return GroupNode(token, contents, NameNode(closeParen))
    }
}