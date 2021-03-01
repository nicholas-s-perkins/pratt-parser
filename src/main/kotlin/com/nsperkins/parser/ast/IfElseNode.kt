package com.nsperkins.parser.ast

import com.nsperkins.parser.token.Token

class IfElseNode(val ifToken: Token, val condition: AstNode, val thenBranch: AstNode, val elseBranch: AstNode) : AstNode {

    override fun printExpression(builder: StringBuilder) {
        builder.append(ifToken)
        builder.append(" ")
        condition.printExpression(builder)
        builder.append(" then ")
        thenBranch.printExpression(builder)
        builder.append(" else ")
        elseBranch.printExpression(builder)
        builder.append(" ")
    }

    override fun getToken() = ifToken
    override fun getChildren(): List<AstNode> = listOf(condition, thenBranch, elseBranch)
}