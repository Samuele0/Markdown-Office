package com.github.samuele0.markdownoffice.markdown.states;

import github.samuele0.plugins.ParserState;
import github.samuele0.plugins.ParserStateProvider;
import github.samuele0.plugins.document.DocumentNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LineStartStateTest {
    @Mock
    ParserStateProvider provider;
    @Mock
    DocumentNode node;

    LineStartState lineStartState;

    @Before
    public void setUp() throws Exception {
        lineStartState = new LineStartState(node, provider);
    }

    @Test
    public void testEmptySpace() {
        ParserState state = lineStartState.accept(' ');
        assertSame(lineStartState, state);
    }

    @Test
    public void testNewLine() {
        ParserState state = lineStartState.accept('\n');
        assertSame(lineStartState, state);
    }

    @Test
    public void testSectionStart() {
        testCombination("section", '#');
    }

    @Test
    public void testItemize() {
        testCombination("itemize?", '*');
    }

    @Test
    public void testBlock() {
        testCombination("block1", '~');
    }

    @Test
    public void testText() {
        testCombination("text", 'a');
    }

    private void testCombination(String value, char symbol) {
        ParserState fakeState = mock(ParserState.class);
        when(provider.get(eq(value), any())).thenReturn(fakeState);
        when(fakeState.accept(anyChar())).thenReturn(fakeState);
        ParserState state = lineStartState.accept(symbol);
        verify(provider, times(1)).get(eq(value), any());
        assertSame(fakeState, state);
    }
}