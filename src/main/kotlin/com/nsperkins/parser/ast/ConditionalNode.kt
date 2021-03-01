package com.nsperkins.parser.ast

//A ternary conditional expression like "a ? b : c
class ConditionalNode(
    private val condition: AstNode? = null,
    private val thenArm: AstNode? = null,
    private val elseArm: AstNode? = null
) : AstNode {

    override fun print(builder: StringBuilder) {
        builder.append("(")
        condition?.print(builder)
        builder.append(" ? ")
        thenArm?.print(builder)
        builder.append(" : ")
        elseArm?.print(builder)
        builder.append(")")
    }

}