package com.nsperkins.parser.ast

import com.nsperkins.parser.token.Token

class NameExpression(val name: String) : Expression {
    override fun print(builder: StringBuilder) {
        builder.append(name)
    }
}