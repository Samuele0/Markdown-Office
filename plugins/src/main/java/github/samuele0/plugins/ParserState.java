package github.samuele0.plugins;

import github.samuele0.plugins.document.DocumentNode;
/**
 * Implementation of a state in the state machine.
 * <p>
 * A state has in his local memory the following information:
 * <ol>
 *     <li>The Document node which is currently being built,</li>
 *     <li>The ParserStateProvider in use</li>
 *     <li>Optionally the previous parser state</li>
 * </ol>
 */
public abstract class ParserState {
    private DocumentNode parent;
    private ParserStateProvider provider;
    private ParserState previous;

    public ParserState(DocumentNode parent, ParserStateProvider provider) {
        this.parent = parent;
        this.provider = provider;
    }

    public ParserStateProvider getProvider() {
        return provider;
    }

    protected DocumentNode getParent() {
        return parent;
    }

    protected void setParent(DocumentNode parent) {
        this.parent = parent;
    }

    /**
     * Accept a single character and returns the next state.
     *
     * @param a the next character in the input stream
     * @return the next state of the state machine
     */
    public abstract ParserState accept(char a);

    public ParserState getPrevious() {
        return previous;
    }

    public void setPrevious(ParserState previous) {
        this.previous = previous;
    }
}
