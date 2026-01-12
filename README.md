## studying compilers every day.

## day 08
- in the last chapter, we wrote a scanner that took the raw source code and transforms it into a series of token.
- the **parser**, we'll write will take this output and transforms yet again into another format.
<img width="785" height="156" alt="Screenshot 2026-01-12 at 22 35 32" src="https://github.com/user-attachments/assets/31000cc7-e9f9-4472-8bbc-085f1b8e4af2" />



## day 07
- identifier = pattern; reserved word = identifier + special meaning + forbidden for user naming; in other words, a reserved word is just an identifier that has been claimed by the language (has a semantic meaning)
- **Maximal munch**: means the lexer always consumes the longest possible sequence of characters that form a token and only then the lexer checks if it's an **identifier** or a **reserved word**


## day 06
- Identify string and number literals

## day 05
- Handled division character

## day 04
- Error handling is part of the language's UI; generator detects errors and reporter displays them.
- Lexemes: smallest meaningful substring of source code.
- Tokens: lexemes + type + literal vlaue + location info
- Scanner: groups lexems and emits tokens (Lexical analygator)
- "Lookahead", needed for multicharacters (!=, >=,...)
- Lexical errors should be reported but scanning should not stop, in order to catch multiple errors.


## day 03
- Switch back to java; because apparently it's not easy to read the book, understand the concepts and deal with C shenanigans.
- jlox is the language i'm implementing, it's a scripting language, which means it executes directly from source.

## day 02
- i'm not very good at coding in C; i might need some time to refresh my memory on this "empty" language :)
- start implementing the scanner. "Scanning" or "lexing: lexical analysis" is the first step in any compiler or interpreter.

## day 01
- prepare resources 
- setup the git repo
