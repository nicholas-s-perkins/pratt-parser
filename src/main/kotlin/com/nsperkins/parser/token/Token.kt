package com.nsperkins.parser.token

abstract class Token(val value: String)
open class Plus(value: String) : Token(value)
open class Numeric(value: String) : Token(value)
