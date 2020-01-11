package github.samuele0.plugins.document;

public class TextNode extends DocumentNode {
    private String text;

    public TextNode(String s) {
        text = s;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
