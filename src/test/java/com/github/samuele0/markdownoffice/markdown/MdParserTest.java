package com.github.samuele0.markdownoffice.markdown;

import com.github.samuele0.markdownoffice.document.DocumentNode;
import com.github.samuele0.markdownoffice.document.DocumentRoot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.Reader;

import static org.junit.Assert.*;
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
