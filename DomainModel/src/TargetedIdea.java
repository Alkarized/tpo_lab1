public class TargetedIdea extends Idea{

    private final ITargetable target;

    public TargetedIdea(String content, ITargetable target) {
        super(content);
        this.target = target;
    }

    @Override
    public String getTargetName() {
        return super.getTargetName() + ", Idea target: [" + this.target.getTargetName() + "]";
    }
}
