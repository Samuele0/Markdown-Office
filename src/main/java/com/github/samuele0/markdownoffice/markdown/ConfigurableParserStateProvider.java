package com.github.samuele0.markdownoffice.markdown;

import github.samuele0.plugins.ParserState;
import github.samuele0.plugins.ParserStateProvider;
import github.samuele0.plugins.document.DocumentNode;
import com.github.samuele0.markdownoffice.markdown.states.LineStartState;
import com.github.samuele0.markdownoffice.markdown.states.SectionState;
import com.github.samuele0.markdownoffice.markdown.states.TextState;

import java.util.HashMap;
import java.util.function.BiFunction;

public class ConfigurableParserStateProvider implements ParserStateProvider {
    private HashMap<String, BiFunction<ParserStateProvider, DocumentNode, ParserState>> map;

    public ConfigurableParserStateProvider() {
        map = new HashMap<>();
        configureDefault();
    }

    private void configureDefault() {
        set("root", (parserStateProvider, node) -> new LineStartState(node, parserStateProvider));
        set("newline", (parserStateProvider, node) -> new LineStartState(node, parserStateProvider));
        set("text", (parserStateProvider, node) -> new TextState(node, parserStateProvider));
        set("section", (parserStateProvider, node) -> new SectionState(node, parserStateProvider));

    }

    @Override
    public ParserState get(String stateName, DocumentNode parent) {
        return map.getOrDefault(stateName, (a, b) -> null).apply(this, parent);
    }

    public void set(String name, BiFunction<ParserStateProvider, DocumentNode, ParserState> factory) {
        map.put(name, factory);
    }


}
