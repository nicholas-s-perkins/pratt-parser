package com.nsperkins.parser.ast

import com.nsperkins.parser.BTreePrinter
import com.nsperkins.parser.token.Token

class AstNode(
    val token: Token,
    val left: AstNode? = null,
    val right: AstNode? = null
) {
    /**
     * Basic tree print function from https://stackoverflow.com/a/8948691
     */
    override fun toString(): String {
        return BTreePrinter.printNode(this)
    }
}
