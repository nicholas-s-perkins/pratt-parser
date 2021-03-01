package com.nsperkins.parser.ast


//base of all AST nodes
interface Expression {
    /**
     * Pretty-print the expression to a string.
     */
    fun print(builder: StringBuilder)
}