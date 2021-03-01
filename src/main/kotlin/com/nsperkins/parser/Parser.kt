package com.nsperkins.parser


import com.nsperkins.parser.ast.Expression
import com.nsperkins.parser.token.*
import java.util.*
import kotlin.reflect.KClass
import kotlin.system.exitProcess


class Parser(tokens: List<Token>) {
    private val tokenStream: Deque<Token> = ArrayDeque(tokens)
    private val tokenToNud: Map<KClass<out Token>, PrefixParselet>
    private val tokenToLed: Map<KClass<out Token>, InfixParslet>

    init {
        val nuds = mutableMapOf<KClass<out Token>, PrefixParselet>()
        nuds[Token.Name::class] = NameParselet()
        nuds[Token.Plus::class] = PrefixOperatorParselet()
        nuds[Token.Minus::class] = PrefixOperatorParselet()
        tokenToNud = nuds.toMap()

        val leds = mutableMapOf<KClass<out Token>, InfixParslet>()
        leds[Token.Plus::class] = BinaryOperatorParselet(Precidence.SUM)
        leds[Token.Minus::class] = BinaryOperatorParselet(Precidence.SUM)
        leds[Token.Multiply::class] = BinaryOperatorParselet(Precidence.PRODUCT)
        tokenToLed = leds.toMap()
    }

    fun next() = tokenStream.removeFirst()
    fun peek() = tokenStream.peek()
    fun hasNext() = tokenStream.isNotEmpty()

    fun parse(rightBindingPower: Int = 0): Expression {
        var token: Token = next()
        val code: PrefixParselet = nudCode(token)

        var left: Expression = code.parse(this, token)

        while (hasNext() && rightBindingPower < leftBindingPower()) {
            token = next()
            val code2 = ledCode(token)!! //todo what if it has no led?
            left = code2.parse(this, left, token)
        }

       return left;
    }

    private fun leftBindingPower(): Int {
        val parslet = tokenToLed[peek()::class]

        return if (parslet != null) parslet.precedence else 0
    }


    //"prefix" parslets
    private fun nudCode(token: Token): PrefixParselet {
        return tokenToNud[token::class] ?: throw IllegalStateException("cannot parse $token")
    }

    private fun ledCode(token: Token): InfixParslet? {
        return tokenToLed[token::class]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val tokenizer = Tokenizer()
            val tokens = tokenizer.tokenize("a - b * c")
            val parser = Parser(tokens)

            val ast = parser.parse()

            val str = StringBuilder();
            ast.print(str)

            println(str.toString())

            exitProcess(0)
        }
    }

}
