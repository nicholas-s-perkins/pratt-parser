package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.AstNode

interface NudCode {
    fun parse(parser: Parser, token: Token): AstNode
}