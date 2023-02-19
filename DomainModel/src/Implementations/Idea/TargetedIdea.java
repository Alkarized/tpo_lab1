package Implementations.Idea;

import Abstractions.ITargetable;
import Enums.IdeaContent;

public class TargetedIdea extends Idea{

    private final ITargetable target;

    public TargetedIdea(IdeaContent content, ITargetable target) {
        super(content);
        this.target = target;
    }

    public ITargetable getTarget() {
        return target;
    }

    @Override
    public String getTargetName() {
        return super.getTargetName() + ", Idea target: [" + this.target.getTargetName() + "]";
    }
}
