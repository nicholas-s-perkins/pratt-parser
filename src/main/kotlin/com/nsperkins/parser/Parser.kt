package com.nsperkins.parser

import com.nsperkins.parser.ast.AstNode
import com.nsperkins.parser.token.*


class Parser(tokens: List<Token>) {
    private val tokenIt = java.util.ArrayDeque(tokens)

    private fun next() = tokenIt.remove()
    private fun hasNext() = !tokenIt.isEmpty()
    private fun peek() = tokenIt.peek()

    fun parse(rbp:Int = 0): AstNode {
        var left = nud(next())

        while (hasNext() && bp(peek()) > rbp){
            left = led(left, next())
        }

        return left
    }

    /**
     * bindingPower
     */
    private fun bp(token: Token): Int {
        return token.bindingPower
    }

    //TODO: probably need to figure out nud/led for tokens more correctly?

    /**
     * null-denotation
     */
    private fun nud(token: Token): AstNode {
        return AstNode(token)
    }

    /**
     * left-denotation
     */
    private fun led(left: AstNode, token: Token): AstNode {
        return AstNode(token = token, left = left, right = parse(bp(token)))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val tokenizer = Tokenizer()
            val parser = Parser(tokenizer.tokenize("1 * (2 + 3)"))

            val ast = parser.parse()
            println(ast)
        }
    }

}
