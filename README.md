# Example Pratt Parser (Kotlin)
Pratt parser in kotlin example based paper _Top Down Operator Precedence_ by Vaughan R. Pratt

https://tdop.github.io/

## Glossary
### Denotations
The pratt parser considers a tokens to have two meanings.
- `nud` - null denotation.  A meaning that has no left context.
- `led` - left denotation.  A meaning that has a left context.

Example in the paper from the paper is the minus sign `-` which has two common forms:
1. `-1`, which would be the `nud` form as a unary operator (it has a single argument `1`)
2. `1-2`, which would be the `led` form as a binary operator (it has two arguments `1` and `2`)


### Binding Power
Tokens have an order of operations.  This is denoted by a tokens "binding power", or really it's precedence compared to other tokens.
E.g. The `+` operator might be `10` and the `*` operator might be `20`, indicating that `*` has precedence over `+`.
 
In the parse, we consider two relationships of this binding power:
- `rbp` - the "right binding power"  
- `lbp` - the "left binding power"
