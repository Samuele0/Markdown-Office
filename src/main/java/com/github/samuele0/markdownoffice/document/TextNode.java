package com.github.samuele0.markdownoffice.document;

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
