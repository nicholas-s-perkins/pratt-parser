package com.nsperkins.parser


import com.nsperkins.parser.token.*

/**
 * Implements the what's necessary for the expression in the TDOP paper:
 * ```
 * if 3*a + b!↑-3 = 0 then print a + (b-1) else rewind
 * ```
 */
class ExampleParser1(tokens: List<Token>) : Parser(
    tokens = tokens,
    tokenToNud = mapOf(
        Token.Name::class to NameCode(),
        Token.Integer::class to NameCode(),
        Token.Plus::class to PrefixOperatorCode(),
        Token.Minus::class to PrefixOperatorCode(),
        Token.Print::class to PrefixOperatorCode(),
        Token.Rewind::class to NameCode(),
        Token.OpenParen::class to GroupCode(),
        Token.CloseParen::class to ErrNud(),
        Token.If::class to IfElseCode(),
    ),
    tokenToLed = mapOf(
        Token.Plus::class to BinaryOperatorCode(),
        Token.Minus::class to BinaryOperatorCode(),
        Token.Multiply::class to BinaryOperatorCode(),
        Token.Equals::class to BinaryOperatorCode(),
        Token.Exponent::class to BinaryOperatorCode(),
        Token.Bang::class to PostFixOperatorCode(),
        Token.CloseParen::class to ErrLed(),
    )
){
}