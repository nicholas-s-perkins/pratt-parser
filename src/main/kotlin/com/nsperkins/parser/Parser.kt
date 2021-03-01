package com.nsperkins.parser


import com.nsperkins.parser.ast.AstNode
import com.nsperkins.parser.token.*
import java.util.*
import kotlin.reflect.KClass


/**
 * Implementation of a Pratt Parser.
 */
open class Parser(
    tokens: List<Token>,
    private val tokenToNud: Map<KClass<out Token>, NudCode>,
    private val tokenToLed: Map<KClass<out Token>, LedCode>
) {
    private val tokenStream: Deque<Token> = ArrayDeque(tokens)

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

    // "prefix" code
    private fun nudCode(token: Token): NudCode {
        return tokenToNud[token::class] ?: throw IllegalStateException("no nud parse for token $token")
    }

    // "infix" code
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
}
