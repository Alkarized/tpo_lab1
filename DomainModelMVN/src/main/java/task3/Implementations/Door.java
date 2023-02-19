package task3.Implementations;

import task3.Abstractions.IMaterialized;
import task3.Enums.Material;

public class Door implements IMaterialized {

    private final Material material;

    public Door(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public String getTargetName() {
        return "The task3.Implementations.task3.Implementations.Door[" + this.hashCode() + "]";
    }
}
