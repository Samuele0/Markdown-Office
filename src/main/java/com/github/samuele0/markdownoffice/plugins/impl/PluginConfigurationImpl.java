package com.github.samuele0.markdownoffice.plugins.impl;

import com.github.samuele0.markdownoffice.markdown.ConfigurableParserStateProvider;
import com.google.inject.Inject;
import github.samuele0.plugins.ParserState;
import github.samuele0.plugins.ParserStateProvider;
import github.samuele0.plugins.PluginConfiguration;
import github.samuele0.plugins.document.DocumentNode;

import java.util.function.BiFunction;

public class PluginConfigurationImpl implements PluginConfiguration {
    ConfigurableParserStateProvider parserStateProvider;

    @Inject
    public PluginConfigurationImpl(ConfigurableParserStateProvider parserStateProvider) {
        this.parserStateProvider = parserStateProvider;
    }

    @Override
    public void addStateFactory(String stateName, BiFunction<ParserStateProvider, DocumentNode, ParserState> map) {

    }
}
