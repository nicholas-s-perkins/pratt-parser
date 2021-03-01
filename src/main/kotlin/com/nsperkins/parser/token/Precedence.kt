package com.nsperkins.parser.token

object Precedence {
    const val NO_BINDING_POWER = -1;
    const val ASSIGNMENT = 1
    const val CONDITIONAL = 2
    const val END_GROUP = 3
    const val EQUALS = 4
    const val SUM = 5
    const val PRODUCT = 6
    const val EXPONENT = 7
    const val PREFIX = 8
    const val POSTFIX = 9
    const val CALL = 10
}