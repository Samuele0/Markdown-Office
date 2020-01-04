package com.github.samuele0.markdownoffice.markdown;

import com.github.samuele0.markdownoffice.document.DocumentNode;
import com.github.samuele0.markdownoffice.document.DocumentRoot;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Creates a Document Tree based on a markdown string.
 * <p>
 * Parsing markdown is handled with a finite state machine, the transition between states are handled by a ParserStateProvider
 */
public class MdParser {
    private static Logger log = LogManager.getLogger();
    private ParserStateProvider provider;

    /**
     * Create a new MdParser which uses the specified provider
     *
     * @param provider the actual implementation of the state machine
     */
    @Inject
    public MdParser(ParserStateProvider provider) {
        this.provider = provider;
    }

    /**
     * Parse the markdown into the document tree
     *
     * @param reader the source of markdown characters
     * @return the document tree
     */
    public DocumentNode parse(Reader reader) throws IOException {
        DocumentRoot root = new DocumentRoot();
        ParserState state = provider.get("root", root);
        int ch;
        while ((ch = reader.read()) != -1)
            state = state.accept((char) ch);
        return root;
    }

    /**
     * Wrapper around parser function which automatically creates a reader around the string
     */
    DocumentNode parse(String markdown) {
        Reader reader = new StringReader(markdown);
        DocumentNode ret = null;
        try {
            ret = parse(reader);
            reader.close();
        } catch (IOException e) {
            log.error(e);
        }
        return ret;
    }
}
