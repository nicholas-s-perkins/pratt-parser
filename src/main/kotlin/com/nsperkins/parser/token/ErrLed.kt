package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.AstNode

class ErrLed : LedCode {
    override fun parse(parser: Parser, left: AstNode, token: Token): AstNode {
        throw IllegalStateException("Illegal token found '$token'")
    }
}