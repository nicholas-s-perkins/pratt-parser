package com.nsperkins.parser.token

sealed class Token(val value: String, val bindingPower: Int) {
    override fun toString(): String {
        return value
    }

    sealed class Operator(value: String, bindingPower: Int) : Token(value, bindingPower) {
        class Plus : Operator("+", 10)
        class Minus : Operator("-", 10)
        class Multiply : Operator("*", 20)
        class OpenParen : Operator("(", 100)
        class CloseParen : Operator(")", 100)
    }

    sealed class Value(value: String) : Token(value, 0) {
        class Integer(value: String) : Value(value)
    }
}
