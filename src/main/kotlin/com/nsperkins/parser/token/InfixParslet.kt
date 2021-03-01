package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.Expression

interface InfixParslet {
    val precedence: Int
    fun parse(parser: Parser, left: Expression, token: Token): Expression
}