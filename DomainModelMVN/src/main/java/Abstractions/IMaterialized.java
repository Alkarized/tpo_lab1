package Abstractions;

import Abstractions.ITargetable;
import Enums.Material;

public interface IMaterialized extends ITargetable {
    Material getMaterial();
}
