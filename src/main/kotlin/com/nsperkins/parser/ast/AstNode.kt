package com.nsperkins.parser.ast

import com.nsperkins.parser.token.Token
import java.util.*

//base of all AST nodes
interface AstNode {

    fun getToken(): Token
    fun getChildren(): List<AstNode>

    fun printExpression(builder: StringBuilder)
    fun printExpression(): String {
        val builder = StringBuilder();
        printExpression(builder)
        return builder.toString()
    }

    fun printTree(): String {
        val builder = StringJoiner("\n")
        printTree(builder, this, "", true)
        return builder.toString()
    }

    fun printTree(builder: StringJoiner, tree: AstNode, indent: String, last: Boolean) {
        builder.add(indent + "+- " + "[" + tree.getToken() + "]")

        val childIndent = indent + (if (last) "   " else "|  ")

        val children = tree.getChildren()
        for (i in children.indices) {
            printTree(builder, children[i], childIndent, i == children.size - 1)
        }
    }
}