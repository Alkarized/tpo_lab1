package Implementations;

import Abstractions.IMaterialized;
import Enums.Material;

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
        return "The Implementations.Implementations.Door[" + this.hashCode() + "]";
    }
}
