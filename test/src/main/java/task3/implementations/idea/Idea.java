package task3.implementations.idea;

import task3.abstractions.ITargetable;
import task3.enums.IdeaContent;

public class Idea implements ITargetable {
    private final IdeaContent content;

    public Idea(IdeaContent content) {
        this.content = content;
    }

    public IdeaContent getContent() {
        return content;
    }

    @Override
    public String getTargetName() {
        return "task3.Implementations.Idea.Idea content: [" + this.content + "]";
    }
}
