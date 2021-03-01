package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.Expression

interface PrefixParselet {
    fun parse(parser: Parser, token: Token): Expression
}