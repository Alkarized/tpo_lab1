package Abstractions;

import Abstractions.ITargetable;

public interface INamed extends ITargetable {

    String getName();

    @Override
    default String getTargetName() {
        return getName();
    }
}
