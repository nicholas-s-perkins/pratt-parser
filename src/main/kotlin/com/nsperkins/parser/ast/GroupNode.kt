package com.nsperkins.parser.ast

import com.nsperkins.parser.token.Token


class GroupNode(val openParen: Token, val contents: AstNode, val closeParen: NameNode) : AstNode {
    override fun printExpression(builder: StringBuilder) {
        builder.append(openParen)
        contents.printExpression(builder)
        closeParen.printExpression(builder)
    }

    override fun getToken() = openParen
    override fun getChildren() = listOf(contents, closeParen)
}