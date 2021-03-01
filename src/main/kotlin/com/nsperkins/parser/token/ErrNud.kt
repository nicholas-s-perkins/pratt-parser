package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.AstNode
import java.lang.IllegalStateException

class ErrNud : NudCode {
    override fun parse(parser: Parser, token: Token): AstNode {
        throw IllegalStateException("Token not expected to be by itself: $token")
    }
}