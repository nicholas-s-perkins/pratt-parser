package com.nsperkins.parser

import com.nsperkins.parser.ast.AstNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class ParserTest {

    companion object {
        private lateinit var tokenizer: Tokenizer

        @BeforeAll
        @JvmStatic
        private fun beforeAll() {
            tokenizer = Tokenizer()
        }
    }
//
//    @Test
//    fun parseWithParens() {
//        val parser = Parser(tokenizer.tokenize("1 * ( 2 + 3 )"))
//        val result = parser.parse()
//
//        compareTree(
//            result,
//            """
//            |    *
//            |   / \
//            |  /   \
//            | /     \
//            |/       \
//            |1       (
//            |       / \
//            |      /   \
//            |      +    )
//            |     / \
//            |     2  3
//            """.trimMargin()
//        )
//    }

    private fun compareTree(result: AstNode, expected: String) {
        assertEquals(
            expected.replace(Regex("\\s+$"), ""),
            result.toString().replace(Regex("\\s+$"), "")
        )
    }
}
