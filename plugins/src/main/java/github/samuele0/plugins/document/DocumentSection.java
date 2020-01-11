package github.samuele0.plugins.document;

public class DocumentSection extends DocumentNode {
    private int level;

    public DocumentSection() {
        level = 1;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
