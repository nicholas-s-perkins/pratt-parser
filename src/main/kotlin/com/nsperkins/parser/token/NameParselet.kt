package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.Expression
import com.nsperkins.parser.ast.NameExpression

class NameParselet : PrefixParselet {
    override fun parse(parser: Parser, token: Token): Expression {
        return NameExpression(token.value);
    }
}