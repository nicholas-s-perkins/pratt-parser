package com.nsperkins.parser.ast

import com.nsperkins.parser.ast.Expression

//A ternary conditional expression like "a ? b : c
class ConditionalExpression(
    private val mCondition: Expression? = null,
    private val mThenArm: Expression? = null,
    private val mElseArm: Expression? = null
) : Expression {

    override fun print(builder: StringBuilder) {
        builder.append("(")
        mCondition?.print(builder)
        builder.append(" ? ")
        mThenArm?.print(builder)
        builder.append(" : ")
        mElseArm?.print(builder)
        builder.append(")")
    }

}