package com.nsperkins.parser


import com.nsperkins.parser.ast.AstNode
import com.nsperkins.parser.token.*
import java.util.*
import kotlin.reflect.KClass
import kotlin.system.exitProcess


class Parser(tokens: List<Token>) {
    private val tokenStream: Deque<Token> = ArrayDeque(tokens)
    private val tokenToNud: Map<KClass<out Token>, NudCode>
    private val tokenToLed: Map<KClass<out Token>, LedCode>

    init {
        val nuds = mutableMapOf<KClass<out Token>, NudCode>()
        nuds[Token.Name::class] = NameCode()
        nuds[Token.Plus::class] = PrefixOperatorCode()
        nuds[Token.Minus::class] = PrefixOperatorCode()
        tokenToNud = nuds.toMap()

        val leds = mutableMapOf<KClass<out Token>, LedCode>()
        leds[Token.Plus::class] = BinaryOperatorCode(Precidence.SUM)
        leds[Token.Minus::class] = BinaryOperatorCode(Precidence.SUM)
        leds[Token.Multiply::class] = BinaryOperatorCode(Precidence.PRODUCT)
        tokenToLed = leds.toMap()
    }

    fun next() = tokenStream.removeFirst()
    fun peek() = tokenStream.peek()
    fun hasNext() = tokenStream.isNotEmpty()

    fun parse(rightBindingPower: Int = 0): AstNode {
        //Q_0
        //c ← nud;
        //left ← run c
        var token: Token = next()
        val cNud: NudCode = nudCode(token)
        var left: AstNode = cNud.parse(this, token)

        //Q_1
        //rbp < lpb/
        //advance;
        //c ← led;
        //left ← run c
        while (hasNext() && rightBindingPower < leftBindingPower(peek())) {
            token = next()
            val cLed = ledCode(token)!! //todo what if it has no led?
            left = cLed.parse(this, left, token)
        }

       return left;
    }

    private fun leftBindingPower(token: Token): Int {
        val parslet = tokenToLed[token::class]
        return if (parslet != null) parslet.precedence else 0
    }

    //"prefix" parselets
    private fun nudCode(token: Token): NudCode {
        return tokenToNud[token::class] ?: throw IllegalStateException("cannot parse $token")
    }

    private fun ledCode(token: Token): LedCode? {
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
