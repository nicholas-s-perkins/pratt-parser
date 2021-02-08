package com.nsperkins.parser

import com.nsperkins.parser.token.Token
import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 * Based loosely on tokenizer from http://cogitolearning.co.uk/2013/04/writing-a-parser-in-java-the-tokenizer/
 */
class Tokenizer {

    private val tokenInfos: MutableList<TokenInfo> = mutableListOf()

    init {
        add("\\(") { Token.Operator.OpenParen() }
        add("\\)") { Token.Operator.CloseParen() }
        add("[+]") { Token.Operator.Plus() }
        add("[-]") { Token.Operator.Minus() }
        add("[*]") { Token.Operator.Multiply() }
        add("[0-9]+") { Token.Value.Integer(it) }
    }

    fun add(regex: String, builder: (String) -> Token) {
        tokenInfos.add(TokenInfo(Pattern.compile("^($regex)"), builder))
    }


    fun tokenize(str: String): List<Token> {
        val tokens = mutableListOf<Token>()

        var s = str

        while (s != "") {
            var match = false

            for (info in tokenInfos) {
                val m: Matcher = info.regex.matcher(s)
                if (m.find()) {
                    match = true

                    val tok: String = m.group().trim()

                    tokens.add(info.tokenBuilder(tok))

                    s = m.replaceFirst("").trim()

                    break
                }
            }

            if (!match) throw IllegalArgumentException("Unexpected pattern in input: ${s}")
        }

        return tokens
    }

    private class TokenInfo(val regex: Pattern, val tokenBuilder: (String) -> Token)
}
