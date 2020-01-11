package com.github.samuele0.markdownoffice.markdown;

import github.samuele0.plugins.document.DocumentNode;
import github.samuele0.plugins.document.DocumentRoot;
import com.github.samuele0.markdownoffice.markdown.states.LineStartState;
import com.github.samuele0.markdownoffice.markdown.states.SectionState;
import com.github.samuele0.markdownoffice.markdown.states.TextState;
import github.samuele0.plugins.ParserState;
import github.samuele0.plugins.ParserStateProvider;
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

    ParserStateProvider provider;

    @Before
    public void setUp() {
        provider = new ConfigurableParserStateProvider();
    }

    @Test
    public void testSimple() {
        MdParser mdParser = new MdParser(provider);
        DocumentNode documentNode = mdParser.parse("# Section1\n some paragraph\n## subsection\nparagraph2\n");
        assertTrue(documentNode instanceof DocumentRoot);

    }
}
