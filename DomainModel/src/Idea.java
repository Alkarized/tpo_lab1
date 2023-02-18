public class Idea implements ITargetable {
    private final String content;

    public Idea(String content) {
        this.content = content;
    }

    public String toString() {
        return content;
    }

    @Override
    public String getTargetName() {
        return "Idea content: [" + this.content + "]";
    }
}
