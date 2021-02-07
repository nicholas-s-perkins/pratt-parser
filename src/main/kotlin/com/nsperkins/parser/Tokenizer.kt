package com.nsperkins.parser

import com.nsperkins.parser.token.Numeric
import com.nsperkins.parser.token.Plus
import com.nsperkins.parser.token.Token

class Tokenizer {
    fun tokenize(input: String): List<Token> {
        return input
            .chunked(1)
            .map {
                when {
                    it.matches(Regex("\\d+")) -> {
                        Numeric(it)
                    }
                    it == "+"                 -> {
                        Plus(it)
                    }
                    else                      -> {
                        throw IllegalArgumentException("token ${it} not supported")
                    }
                }
            }
    }
}
