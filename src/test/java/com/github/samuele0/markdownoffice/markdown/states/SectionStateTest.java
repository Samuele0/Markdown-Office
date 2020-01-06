package com.github.samuele0.markdownoffice.markdown.states;

import com.github.samuele0.markdownoffice.document.DocumentNode;
import com.github.samuele0.markdownoffice.document.DocumentSection;
import com.github.samuele0.markdownoffice.markdown.ParserState;
import com.github.samuele0.markdownoffice.markdown.ParserStateProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class SectionStateTest {
    @Mock
    ParserStateProvider provider;
    @Mock
    DocumentSection node;
    @Mock
    ParserState text;
    @Mock
    ParserState newline;
    SectionState sectionState;

    @Before
    public void setUp() throws Exception {
        when(provider.get(eq("text"), any())).thenReturn(text);
        when(provider.get(eq("newline"), any())).thenReturn(newline);

        sectionState = new SectionState(node, provider);
        when(text.accept(anyChar())).thenReturn(text);
    }

    @Test
    public void testNotSection() {
        ParserState ret = sectionState.accept('a');
        verify(text).accept('#');
        verify(text).accept('a');
        assertSame(text, ret);
    }

    @Test
    public void testMultipleNotSection() {
        ParserState ret = sectionState.accept('#').accept('#').accept('a');
        verify(text, times(3)).accept('#');
        verify(text).accept('a');
        assertSame(text, ret);
    }

    @Test
    public void testSectionLevels() {
        DocumentNode fakenode = mock(DocumentNode.class);
        when(node.getParent()).thenReturn(fakenode);
        when(node.getLevel()).thenReturn(1);
        ParserState state = sectionState.accept('\n');
        verify(node).getParent();
        verify(provider).get(eq("newline"), any());
        assertSame(newline, state);
    }

    @Test
    public void testSectionText() {
        ParserState ret = sectionState.accept(' ');
        verify(text, never()).accept(anyChar());
        assertSame(text, ret);
    }
}