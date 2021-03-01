package com.nsperkins.parser.ast

import com.nsperkins.parser.token.Token

class NameNode(val name: Token) : AstNode {
    override fun print(builder: StringBuilder) {
        builder.append(name)
    }

    override fun getToken() = name
    override fun getChildren() = listOf<AstNode>()
}