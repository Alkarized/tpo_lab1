package Implementations;

import Abstractions.IMaterialized;
import Abstractions.ITargetable;
import Enums.Material;

public class HumanBrain implements IMaterialized {

    @Override
    public String getTargetName() {
        return "HumanBrain[" + hashCode() + "]";
    }

    @Override
    public Material getMaterial() {
        return Material.FLESH;
    }
}
