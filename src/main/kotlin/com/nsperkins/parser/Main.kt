package com.nsperkins.parser

fun main() {
    val tokenizer = Tokenizer()
    val tokens = tokenizer.tokenize("if 3*a + b!↑-3 = 0 then print a + (b-1) else rewind")
//            val tokens = tokenizer.tokenize("a - b * c")
//            val tokens = tokenizer.tokenize("if a+b then (b-1) else c")
    val parser = ExampleParser1(tokens)

    val ast = parser.parse()

    println(ast.printExpression())
    println(ast.printTree())

}