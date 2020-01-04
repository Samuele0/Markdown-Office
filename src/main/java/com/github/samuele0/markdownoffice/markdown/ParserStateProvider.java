package com.github.samuele0.markdownoffice.markdown;

import com.github.samuele0.markdownoffice.document.DocumentNode;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public interface ParserStateProvider {
    ParserState get(String stateName, DocumentNode parent);

    void set(String stateName, BiFunction<DocumentNode, ParserStateProvider, ParserState> supplier);
}
