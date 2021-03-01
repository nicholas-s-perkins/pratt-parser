package com.nsperkins.parser


import com.nsperkins.parser.ast.Expression
import com.nsperkins.parser.token.*
import java.util.*
import kotlin.reflect.KClass
import kotlin.system.exitProcess


class Parser(private val tokens: List<Token>) {

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
        leds[Token.Plus::class] = BinaryOperatorParselet()
        leds[Token.Minus::class] = BinaryOperatorParselet()
        tokenToLed = leds.toMap()
    }

    fun next() = tokenStream.removeFirst()
    fun peek() = tokenStream.peek()
    fun hasNext() = tokenStream.isNotEmpty()


    fun parse(): Expression {
        var token: Token = next()
        val code: PrefixParselet = nudCode(token)

        val left: Expression = code.parse(this, token)

        if(!hasNext()) return left;

        token = peek()

        //led
        val infix: InfixParslet? = ledCode(token)

        if (infix == null) return left;

        next()

        return infix.parse(this, left, token)
    }

    //"prefix" parslets
    private fun nudCode(token: Token): PrefixParselet {
        return tokenToNud[token::class] ?: throw IllegalStateException("cannot parse $token")
    }

    private fun ledCode(token: Token): InfixParslet? {
        return tokenToLed[token::class]
    }


//    fun register(token: TokenType, parselet: InfixParselet) {
//        infixParselets[token] = parselet
//    }

//    fun parse(precedence: Int): Expression {
//        var token = consume()
//        val prefix = prefixParselets[token.getType()] ?: throw ParseException("Could not parse \"" + token.getText().toString() + "\".")
//
//        var left = prefix.parse(this, token)
//
//        while (precedence < precedence) {
//            token = consume()
//            val infix: InfixParselet? = infixParselets[token.getType()]
//            left = infix.parse(this, left, token)
//        }
//        return left
//    }
//
//    fun parse(): Expression {
//        return parse(0)
//    }

//    fun match(expected: TokenType?): Boolean {
//        val token = lookAhead(0)
//        if (token.getType() !== expected) {
//            return false
//        }
//        consume()
//        return true
//    }
//
//    fun consume(expected: TokenType): Token {
//        val token = lookAhead(0)
//        if (token.getType() !== expected) {
//            throw RuntimeException(
//                "Expected token " + expected +
//                        " and found " + token.getType()
//            )
//        }
//        return consume()
//    }
//
//    fun consume(): Token {
//        // Make sure we've read the token.
//        lookAhead(0)
//        return read.removeAt(0)
//    }
//
//    private fun lookAhead(distance: Int): Token {
//        // Read in as many as needed.
//        while (distance >= read.size) {
//            read.add(mTokens.next())
//        }
//
//        // Get the queued token.
//        return read[distance]
//    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val tokenizer = Tokenizer()
            val tokens = tokenizer.tokenize("a - b - c")
            val parser = Parser(tokens)

            val ast = parser.parse()

            val str = StringBuilder();
            ast.print(str)

            println(str.toString())

            exitProcess(0)
        }
    }

}
