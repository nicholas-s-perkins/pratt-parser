package com.nsperkins.parser.token

abstract class Token(val value: String, val bindingPower: Int?) {
    override fun toString(): String {
        return value
    }
}

abstract class Operator(value: String, bindingPower: Int?) : Token(value, bindingPower)

open class Plus : Operator("+", 10)
open class Multiply : Operator("*", 20)

open class Numeric(value: String) : Token(value, 0)
