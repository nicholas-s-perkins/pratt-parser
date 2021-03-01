package com.nsperkins.parser.ast

import com.nsperkins.parser.token.Token

/**
 * A null denotation expression or "postfix" expression, such as the bang in `1!`
 */
class PostfixNode(val operator: Token, val left: AstNode) : AstNode {
    override fun printExpression(builder: StringBuilder) {
        builder.append("(")
        left.printExpression(builder)
        builder.append(")")
        builder.append(operator.toString())
    }

    override fun getToken() = operator
    override fun getChildren() = listOf(left)
}