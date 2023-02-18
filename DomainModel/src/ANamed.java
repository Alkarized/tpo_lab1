public abstract class ANamed implements ITargetable {
    private final String name;

    public ANamed(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getTargetName() {
        return getName();
    }
}
