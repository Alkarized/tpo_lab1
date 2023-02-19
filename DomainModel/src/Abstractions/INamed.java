package Abstractions;

public interface INamed extends ITargetable {

    String getName();

    @Override
    default String getTargetName() {
        return getName();
    }
}
