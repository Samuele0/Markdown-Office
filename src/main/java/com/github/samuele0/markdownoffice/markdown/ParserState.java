package com.github.samuele0.markdownoffice.markdown;

import com.github.samuele0.markdownoffice.document.DocumentNode;
import com.google.inject.Inject;

public abstract class ParserState {
    private DocumentNode parent;
    private ParserStateProvider provider;

    @Inject
    public ParserState(DocumentNode parent, ParserStateProvider provider) {
        this.parent = parent;
        this.provider = provider;
    }

    public ParserStateProvider getProvider() {
        return provider;
    }

    protected DocumentNode getParent() {
        return parent;
    }

    protected void setParent(DocumentNode parent) {
        this.parent = parent;
    }

    public abstract ParserState accept(char a);
}
