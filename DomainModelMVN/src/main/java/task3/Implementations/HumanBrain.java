package task3.Implementations;

import task3.Abstractions.IMaterialized;
import task3.Enums.Material;

public class HumanBrain implements IMaterialized {

    @Override
    public String getTargetName() {
        return "task3.Implementations.HumanBrain[" + hashCode() + "]";
    }

    @Override
    public Material getMaterial() {
        return Material.FLESH;
    }
}
