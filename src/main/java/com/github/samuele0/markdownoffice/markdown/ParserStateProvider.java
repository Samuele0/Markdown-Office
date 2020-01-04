package com.github.samuele0.markdownoffice.markdown;

public interface ParserStateProvider {
    ParserState get(String stateName);
}
