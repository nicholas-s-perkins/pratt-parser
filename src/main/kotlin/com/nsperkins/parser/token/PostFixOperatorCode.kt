package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.AstNode
import com.nsperkins.parser.ast.PostfixNode
import com.nsperkins.parser.ast.PrefixNode

class PostFixOperatorCode : LedCode {
    override fun parse(parser: Parser, left: AstNode, token: Token): AstNode {
        return PostfixNode(token, left)
    }
}
