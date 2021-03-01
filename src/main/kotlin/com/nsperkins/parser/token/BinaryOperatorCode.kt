package com.nsperkins.parser.token

import com.nsperkins.parser.Parser
import com.nsperkins.parser.ast.AstNode
import com.nsperkins.parser.ast.OperatorNode


class BinaryOperatorCode(
    val associativity: Associativity = Associativity.LEFT
) : LedCode {
    override fun parse(
        parser: Parser,
        left: AstNode,
        token: Token
    ): AstNode {
        val right: AstNode = parser.parse(token.bindingPower - (if (associativity == Associativity.RIGHT) 1 else 0))

        return OperatorNode(left, token, right)
    }
}