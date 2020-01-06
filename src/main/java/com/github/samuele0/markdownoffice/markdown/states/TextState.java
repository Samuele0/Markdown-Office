package com.github.samuele0.markdownoffice.markdown.states;

import com.github.samuele0.markdownoffice.document.DocumentNode;
import com.github.samuele0.markdownoffice.document.TextNode;
import com.github.samuele0.markdownoffice.markdown.ParserState;
import com.github.samuele0.markdownoffice.markdown.ParserStateProvider;

/**
 * This state handles the parsing of a single line of markdown textual content.
 * <p>
 * Once the line is terminated this state will return the control to the previous state, sending him a '\n'
 * <p>
 * To use this state in necessary to manually set the previous state
 */
public class TextState extends ParserState {
    private StringBuilder builder;

    public TextState(DocumentNode parent, ParserStateProvider provider) {
        super(parent, provider);
        builder = new StringBuilder();
    }

    @Override
    public ParserState accept(char a) {
        switch (a) {
            case '\n':
                getParent().addChild(new TextNode(builder.toString()));
                return getPrevious().accept('\n');
            default:
                builder.append(a);
                return this;
        }
    }
}
