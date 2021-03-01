package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.AstNode
import com.nsperkins.parser.ast.PrefixNode

class PrefixOperatorCode : NudCode {
    override fun parse(parser: Parser, token: Token): AstNode {
        val operand: AstNode = parser.parse()
        return PrefixNode(token, operand)
    }
}
