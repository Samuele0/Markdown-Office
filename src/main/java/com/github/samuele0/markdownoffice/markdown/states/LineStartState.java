package com.github.samuele0.markdownoffice.markdown.states;

import com.github.samuele0.markdownoffice.document.DocumentNode;
import com.github.samuele0.markdownoffice.markdown.ParserState;
import com.github.samuele0.markdownoffice.markdown.ParserStateProvider;

/**
 * This state handles redirecting the parsing of the line to the proper state.
 */
public class LineStartState extends ParserState {
    public LineStartState(DocumentNode parent, ParserStateProvider provider) {
        super(parent, provider);
    }

    @Override
    public ParserState accept(char a) {
        switch (a) {
            case '#':
                return getProvider().get("section", getParent());
            case '*':
                return getProvider().get("itemize?", getParent());
            case '~':
                return getProvider().get("block1", getParent());
            case ' ':
            case '\n':
                return this;
            default:
                return getProvider().get("text", getParent());
        }
    }
}
