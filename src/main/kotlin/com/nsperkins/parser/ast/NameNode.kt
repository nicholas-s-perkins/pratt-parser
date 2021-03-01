package com.nsperkins.parser.ast

class NameNode(val name: String) : AstNode {
    override fun print(builder: StringBuilder) {
        builder.append(name)
    }
}