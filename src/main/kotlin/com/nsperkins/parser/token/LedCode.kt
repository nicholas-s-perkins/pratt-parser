package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.AstNode

interface LedCode {
    val precedence: Int
    fun parse(parser: Parser, left: AstNode, token: Token): AstNode
}