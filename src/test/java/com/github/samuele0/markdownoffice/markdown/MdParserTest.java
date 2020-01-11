package com.github.samuele0.markdownoffice.markdown;

import github.samuele0.plugins.ParserState;
import github.samuele0.plugins.ParserStateProvider;
import github.samuele0.plugins.document.DocumentNode;
import github.samuele0.plugins.document.DocumentRoot;
import org.apache.logging.log4j.core.Appender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MdParserTest {
    @Mock
    ParserStateProvider provider;
    @Mock
    ParserState state;
    @Mock
    Appender appender;

    MdParser parser;


    @Before
    public void setUp() throws Exception {
        when(provider.get(eq("root"), any())).thenReturn(state);
        when(state.accept(anyChar())).thenReturn(state);
        parser = new MdParser(provider);
    }

    @Test
    public void testReturnType() {
        String source = "aaaa";
        DocumentNode tree = parser.parse(source);
        assertTrue(tree instanceof DocumentRoot);
    }

    @Test
    public void testProviderCall() {
        String source = "aaaa";
        DocumentNode tree = parser.parse(source);
        verify(provider, times(1)).get(eq("root"), any(DocumentRoot.class));
    }

    @Test
    public void testStateCall() {
        String source = "aaaa";
        DocumentNode tree = parser.parse(source);
        verify(state, times(4)).accept('a');
    }
}
