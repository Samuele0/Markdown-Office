package github.samuele0.plugins;

import github.samuele0.plugins.document.DocumentNode;

import java.util.function.BiFunction;

public interface PluginConfiguration {
    void addStateFactory(String stateName, BiFunction<ParserStateProvider, DocumentNode, ParserState> map);
}
