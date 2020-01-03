package com.github.samuele0.markdownoffice.markdown;

public abstract class ParserState {
    public abstract ParserState accept(char a);
}
