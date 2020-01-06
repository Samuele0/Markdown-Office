package com.github.samuele0.markdownoffice.markdown.states;

import com.github.samuele0.markdownoffice.document.DocumentNode;
import com.github.samuele0.markdownoffice.document.TextNode;
import com.github.samuele0.markdownoffice.markdown.ParserState;
import com.github.samuele0.markdownoffice.markdown.ParserStateProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TextStateTest {
    @Mock
    ParserStateProvider provider;
    @Mock
    DocumentNode node;
    @Mock
    ParserState previous;

    TextState state;

    @Before
    public void setUp() throws Exception {
        state = new TextState(node, provider);
        state.setPrevious(previous);

    }

    @Test
    public void testReturn() {
        ParserState ret = state.accept('a');
        assertSame(state, ret);
    }

    @Test
    public void testNewLine() {
        ParserState ret = state.accept('a').accept('\n');
        ArgumentCaptor<DocumentNode> captor = ArgumentCaptor.forClass(DocumentNode.class);
        verify(node, times(1)).addChild(captor.capture());
        DocumentNode captured = captor.getValue();
        assertTrue(captured instanceof TextNode);
        verify(previous, times(1)).accept('\n');
        assertEquals("a", ((TextNode) captured).getText());
    }
}