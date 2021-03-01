package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.AstNode
import com.nsperkins.parser.ast.NameNode

class NameCode : NudCode {
    override fun parse(parser: Parser, token: Token): AstNode {
        return NameNode(token.value);
    }
}