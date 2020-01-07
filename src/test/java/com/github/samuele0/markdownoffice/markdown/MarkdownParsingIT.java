package com.github.samuele0.markdownoffice.markdown;

import com.github.samuele0.markdownoffice.document.DocumentNode;
import com.github.samuele0.markdownoffice.document.DocumentRoot;
import com.github.samuele0.markdownoffice.markdown.states.LineStartState;
import com.github.samuele0.markdownoffice.markdown.states.SectionState;
import com.github.samuele0.markdownoffice.markdown.states.TextState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer2;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.answer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarkdownParsingIT {
    @Mock
    ParserStateProvider provider;

    @Before
    public void setUp() {
        when(provider.get(anyString(), any())).then(answer((Answer2<ParserState, String, DocumentNode>) (argument0, argument1) -> {
            switch (argument0) {
                case "root":
                case "newline":
                    return new LineStartState(argument1, provider);
                case "text":
                    return new TextState(argument1, provider);
                default:
                    return new SectionState(argument1, provider);
            }
        }));
    }

    @Test
    public void testSimple() {
        MdParser mdParser = new MdParser(provider);
        DocumentNode documentNode = mdParser.parse("# Section1\n some paragraph\n## subsection\nparagraph2\n");
        assertTrue(documentNode instanceof DocumentRoot);

    }
}
