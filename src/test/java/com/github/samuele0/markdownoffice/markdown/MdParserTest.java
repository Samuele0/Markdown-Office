package com.github.samuele0.markdownoffice.markdown;

import com.github.samuele0.markdownoffice.document.DocumentNode;
import com.github.samuele0.markdownoffice.document.DocumentRoot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MdParserTest {
    @Mock
    ParserStateProvider provider;
    @Mock
    ParserState state;

    MdParser parser;

    @Before
    public void setUp() throws Exception {
        when(provider.get("root")).thenReturn(state);
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
        verify(provider, times(1)).get("root");
    }

    @Test
    public void testStateCall() {

        String source = "aaaa";
        DocumentNode tree = parser.parse(source);
        verify(state, times(4)).accept('a');
    }
}