package com.nsperkins.parser


import com.nsperkins.parser.ast.AstNode
import com.nsperkins.parser.token.*
import java.util.*
import kotlin.reflect.KClass


class Parser(tokens: List<Token>) {
    private val tokenStream: Deque<Token> = ArrayDeque(tokens)
    private val tokenToNud: Map<KClass<out Token>, NudCode>
    private val tokenToLed: Map<KClass<out Token>, LedCode>

    init {
        val nuds = mutableMapOf<KClass<out Token>, NudCode>()
        nuds[Token.Name::class] = NameCode()
        nuds[Token.Integer::class] = NameCode()
        nuds[Token.Plus::class] = PrefixOperatorCode()
        nuds[Token.Minus::class] = PrefixOperatorCode()
        nuds[Token.Print::class] = PrefixOperatorCode()
        nuds[Token.Rewind::class] = NameCode()
        nuds[Token.OpenParen::class] = GroupCode()
        nuds[Token.CloseParen::class] = ErrNud()
        nuds[Token.If::class] = IfElseCode()
        tokenToNud = nuds.toMap()

        val leds = mutableMapOf<KClass<out Token>, LedCode>()
        leds[Token.Plus::class] = BinaryOperatorCode()
        leds[Token.Minus::class] = BinaryOperatorCode()
        leds[Token.Multiply::class] = BinaryOperatorCode()
        leds[Token.Equals::class] = BinaryOperatorCode()
        leds[Token.Exponent::class] = BinaryOperatorCode()
        leds[Token.Bang::class] = PostFixOperatorCode()
        leds[Token.CloseParen::class] = ErrLed()
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
            val cLed = ledCode(token)
            left = cLed.parse(this, left, token)
        }

       return left;
    }

    private fun leftBindingPower(token: Token): Int {
        return token.bindingPower
    }

    //"prefix" code
    private fun nudCode(token: Token): NudCode {
        return tokenToNud[token::class] ?: throw IllegalStateException("no nud parse for token $token")
    }

    // infix code
    private fun ledCode(token: Token): LedCode {
        return tokenToLed[token::class]?: throw IllegalStateException("no led found for token $token")
    }

    fun next(kClass: KClass<out Token>): Token {
        val token = next()
        if (token::class != kClass) {
            throw IllegalStateException("expected token of type '${kClass.simpleName}' instead got token '${token}'")
        }
        return token
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val tokenizer = Tokenizer()
            val tokens = tokenizer.tokenize("if 3*a + b!↑-3 = 0 then print a + (b-1) else rewind")
//            val tokens = tokenizer.tokenize("a - b * c")
//            val tokens = tokenizer.tokenize("if a+b then (b-1) else c")
            val parser = Parser(tokens)

            val ast = parser.parse()

            println(ast.printExpression())
            println(ast.printTree())
        }
    }

}
