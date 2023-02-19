package Implementations;

import Abstractions.IMaterialized;
import Enums.Material;

public class HumanBrain implements IMaterialized {

    @Override
    public String getTargetName() {
        return "Implementations.HumanBrain[" + hashCode() + "]";
    }

    @Override
    public Material getMaterial() {
        return Material.FLESH;
    }
}
