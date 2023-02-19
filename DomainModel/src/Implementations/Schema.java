package Implementations;

import Abstractions.IMaterialized;
import Abstractions.ITargetable;
import Enums.Material;
import Enums.State;

public class Schema implements IMaterialized {
    private State currentState;
    private ITargetable lastManipulatedObject;

    private String message;

    public Schema() {
        this.currentState = State.DEFAULT;
        this.message = ". . .";
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
        switch (this.currentState) {
            case DOUBT -> message = "Is it worth it?";
            case DEFAULT -> message = ". . .";
            case DESPAIR -> message = "Totally not worth it...";
            case DISGUST -> message = "It's disgusting!";
            case COLD_CONTEMPT -> message = "Miserable...";
        }
    }

    public void chirp(State state) {
        setCurrentState(state);
        System.out.printf("[%s], being in state of [%s], chirped.\n", this.getTargetName(), this.currentState);
    }

    public void manipulate(ITargetable target) {
        this.lastManipulatedObject = target;
        System.out.printf("[%s], manipulated the [%s].\n", this.getTargetName(), this.lastManipulatedObject.getTargetName());
    }

    public void click() {
        System.out.printf("[%s] clicked.\n", this.getTargetName());
    }

    public void speak() {
        System.out.printf("[%s]: \"%s\"\n", this.getTargetName(), this.message);
    }

    public void compareMaterials(Material first, Material second) {
        System.out.printf("[%s] compared: [%s] vs [%s]. Result: %s.\n", this.getTargetName(), first, second,
                first == second ? "Equals" : "Not equals");
    }

    @Override
    public String getTargetName() {
        return "The schema[" + hashCode() + "]";
    }

    @Override
    public Material getMaterial() {
        return Material.MIXED;
    }
}
