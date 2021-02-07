package com.nsperkins.parser

import com.nsperkins.parser.ast.AstNode
import com.nsperkins.parser.token.Multiply
import com.nsperkins.parser.token.Numeric
import com.nsperkins.parser.token.Plus
import com.nsperkins.parser.token.Token


class Parser(tokens: List<Token>) {
    private val tokenIt = tokens.iterator()

    private fun next() = tokenIt.next()
    private fun hasNext() = tokenIt.hasNext()


    fun parse(): AstNode {
        var left: AstNode = nud(next())

        while (hasNext()) {
            left = led(left, next())
        }

        return left
    }

    private fun nud(token: Token): AstNode {
        return when (token) {
            is Numeric -> {
                AstNode(token)
            }
            is Plus    -> {
                AstNode(token)
            }
            else       -> {
                throw IllegalArgumentException("token ${token} not supported")
            }
        }
    }

    private fun led(left: AstNode, token: Token): AstNode {
        return when (token) {
            is Numeric  -> {
                val node = AstNode(token)
                left.right = node
                left
            }
            is Plus     -> {
                val node = AstNode(token)
                node.left = left
                node
            }
            is Multiply -> {
                val node = AstNode(token)
                node.left = left
                node
            }
            else        -> {
                throw IllegalArgumentException("token ${token} not supported")
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val tokenizer = Tokenizer()
            val parser = Parser(tokenizer.tokenize("1+2*3"))

            val ast = parser.parse()
            println(ast)
        }
    }

}
