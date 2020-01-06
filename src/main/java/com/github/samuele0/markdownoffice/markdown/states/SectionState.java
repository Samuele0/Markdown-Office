package com.github.samuele0.markdownoffice.markdown.states;

import com.github.samuele0.markdownoffice.document.DocumentNode;
import com.github.samuele0.markdownoffice.document.DocumentSection;
import com.github.samuele0.markdownoffice.document.Header;
import com.github.samuele0.markdownoffice.markdown.ParserState;
import com.github.samuele0.markdownoffice.markdown.ParserStateProvider;

public class SectionState extends ParserState {
    private DocumentSection section;
    private Header header;

    public SectionState(DocumentNode parent, ParserStateProvider provider) {
        super(parent, provider);
        section = new DocumentSection();
        header = new Header();
        section.addChild(header);
    }

    @Override
    public ParserState accept(char a) {
        if (a == ' ') {
            ParserState textState = getProvider().get("text", header);
            textState.setPrevious(this);
            return textState;
        } else if (a == '\n') {
            placeSection();
            return getProvider().get("newline", section);
        } else if (a == '#') {
            section.setLevel(section.getLevel() + 1);
            return this;
        }

        ParserState textState = getProvider().get("text", getParent());
        textState.setPrevious(getProvider().get("newline", getParent()));
        for (int i = 0; i < section.getLevel(); i++) {
            textState = textState.accept('#');
        }
        return textState.accept(a);
    }

    private void placeSection() {
        DocumentNode father = getParent();
        if (father instanceof DocumentSection && ((DocumentSection) father).getLevel() >= section.getLevel()) {
            setParent(getParent().getParent());
            placeSection();
            return;
        }
        father.addChild(section);
    }
}
