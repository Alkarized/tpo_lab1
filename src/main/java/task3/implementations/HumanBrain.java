package task3.implementations;

import task3.abstractions.IMaterialized;
import task3.enums.Material;

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
