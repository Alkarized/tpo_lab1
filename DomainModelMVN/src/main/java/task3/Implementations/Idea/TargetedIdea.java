package task3.Implementations.Idea;

import task3.Abstractions.ITargetable;
import task3.Enums.IdeaContent;

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
        return super.getTargetName() + ", task3.Implementations.Idea.Idea target: [" + this.target.getTargetName() + "]";
    }
}
