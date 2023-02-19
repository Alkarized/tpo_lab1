package task3.Abstractions;

public interface INamed extends ITargetable {

    String getName();

    @Override
    default String getTargetName() {
        return getName();
    }
}
