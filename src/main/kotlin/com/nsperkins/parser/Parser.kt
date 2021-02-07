package com.nsperkins.parser

import com.nsperkins.parser.ast.AstNode
import com.nsperkins.parser.token.Token


class Parser {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val parser = Parser()
            val tokenizer = Tokenizer()

            val ast = parser.parse(tokenizer.tokenize("1+1"))
            println(ast)
        }
    }

    fun parse(tokens: List<Token>): AstNode {

    }
}
