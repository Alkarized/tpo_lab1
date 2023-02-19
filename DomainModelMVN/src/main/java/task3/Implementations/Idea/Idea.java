package task3.Implementations.Idea;

import task3.Abstractions.ITargetable;
import task3.Enums.IdeaContent;

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
