package github.samuele0.plugins;

import github.samuele0.plugins.document.DocumentNode;

import java.util.function.BiFunction;
import java.util.function.Supplier;

@FunctionalInterface
public interface ParserStateProvider {
    ParserState get(String stateName, DocumentNode parent);
}
