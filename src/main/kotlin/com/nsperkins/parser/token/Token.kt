package com.nsperkins.parser.token

//sometimes interesting to have parse location, so you can point out where an error is
// then your ast can contain the token location

// you could just have tokens, but the AST is what makes it an operator
sealed class Token(
    val value: String,
    val bindingPower: Int
) {
    class Plus : Token("+", Precedence.SUM)
    class Minus : Token("-", Precedence.SUM)
    class Multiply : Token("*", Precedence.PRODUCT)
    class OpenParen : Token("(", Precedence.NO_BINDING_POWER)
    class CloseParen : Token(")", Precedence.NO_BINDING_POWER)

    class Equals: Token("=", Precedence.EQUALS)

    class If: Token("if", Precedence.CONDITIONAL)
    class Then: Token("then", Precedence.CONDITIONAL)
    class Else: Token("else", Precedence.CONDITIONAL)
    class Print: Token("print", Precedence.EQUALS)
    class Rewind: Token("rewind", Precedence.EQUALS)

    class Exponent: Token("↑", Precedence.EXPONENT)
    class Bang: Token("!", Precedence.POSTFIX)

    class Integer(value: String) : Token(value, Precedence.NO_BINDING_POWER)
    class Name(value: String) : Token(value, Precedence.NO_BINDING_POWER)

    override fun toString(): String {
        return value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Token

        if (value != other.value) return false
        if (bindingPower != other.bindingPower) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + bindingPower
        return result
    }
}
