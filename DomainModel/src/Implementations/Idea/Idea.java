package Implementations.Idea;

import Abstractions.ITargetable;
import Enums.IdeaContent;

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
        return "Idea content: [" + this.content + "]";
    }
}
