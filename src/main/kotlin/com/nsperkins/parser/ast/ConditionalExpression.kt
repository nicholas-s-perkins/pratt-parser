package com.nsperkins.parser.ast

//A ternary conditional expression like "a ? b : c
class ConditionalExpression(
    private val condition: Expression? = null,
    private val thenArm: Expression? = null,
    private val elseArm: Expression? = null
) : Expression {

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