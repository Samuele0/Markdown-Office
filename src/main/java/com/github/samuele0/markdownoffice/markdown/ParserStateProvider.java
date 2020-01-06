package com.github.samuele0.markdownoffice.markdown;

import com.github.samuele0.markdownoffice.document.DocumentNode;

import java.util.function.BiFunction;
import java.util.function.Supplier;

@FunctionalInterface
public interface ParserStateProvider {
    ParserState get(String stateName, DocumentNode parent);
}
