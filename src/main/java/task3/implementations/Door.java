package task3.implementations;

import task3.abstractions.IMaterialized;
import task3.enums.Material;

public class Door implements IMaterialized {

    private final Material material;

    public Door(final Material material) {
        this.material = material;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public String getTargetName() {
        return "The task3.Implementations.task3.Implementations.Door[" + this.hashCode() + "]";
    }
}
