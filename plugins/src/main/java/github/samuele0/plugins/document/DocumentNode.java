package github.samuele0.plugins.document;

import java.util.LinkedList;
import java.util.List;

public abstract class DocumentNode {
    private List<DocumentNode> childs;
    private DocumentNode parent;

    public DocumentNode() {
        childs = new LinkedList<>();
    }

    public void addChild(DocumentNode node) {
        childs.add(node);
        node.setParent(this);
    }

    public DocumentNode getParent() {
        return parent;
    }

    public void setParent(DocumentNode parent) {
        this.parent = parent;
    }
}
