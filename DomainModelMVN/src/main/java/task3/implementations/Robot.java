package task3.implementations;

import task3.abstractions.IMaterialized;
import task3.abstractions.INamed;
import task3.abstractions.ITargetable;
import task3.enums.IdeaContent;
import task3.enums.Material;
import task3.enums.State;
import task3.implementations.idea.Idea;
import task3.implementations.idea.TargetedIdea;

public class Robot implements INamed, IMaterialized {

    private final String name;
    private final Schema[] logicalSchemas;
    private State currentState;

    public Robot(String name, int numOfSchemas) {
        this.name = name;
        this.currentState = State.DEFAULT;
        this.logicalSchemas = new Schema[numOfSchemas];
        for (int i = 0; i < numOfSchemas; i++) {
            logicalSchemas[i] = new Schema();
        }
    }

    public String getName() {
        return this.name;
    }

    public State getCurrentState() {
        return currentState;
    }

    public State watch(ITargetable target) {
        if (target.getTargetName().equals("She")) {
            this.currentState = State.COLD_CONTEMPT;
        }
        System.out.printf("[%s], being in state of [%s], watched after [%s].\n", this.getTargetName(), this.currentState, target.getTargetName());
        return this.currentState;
    }

    public State useSchemas(Idea idea) {
        if (this.logicalSchemas.length == 0) {
            return this.currentState;
        }
        if (this.currentState != State.COLD_CONTEMPT) {
            return this.currentState;
        }
        if (idea.getContent() == IdeaContent.PHYSICAL_ABUSE) {
            State currState;
            for (Schema schema : this.logicalSchemas) {
                currState = schema.chirp();
            }
            for (Schema schema : this.logicalSchemas) {
                schema.manipulate(idea);
            }
            for (Schema schema : this.logicalSchemas) {
                currState = schema.click();
            }
            for (Schema schema : this.logicalSchemas) {
                schema.speak();
            }
            if (!(idea instanceof TargetedIdea && ((TargetedIdea) idea).getTarget() instanceof IMaterialized)) {
                this.currentState = this.logicalSchemas[0].getCurrentState();
                return this.currentState;
            }
            for (Schema schema : this.logicalSchemas) {
                currState = schema.compareMaterials(((IMaterialized) ((TargetedIdea) idea).getTarget()).getMaterial(), new HumanBrain().getMaterial());
            }
            for (Schema schema : this.logicalSchemas) {
                currState = schema.checkHydrogen();
            }
            for (Schema schema : this.logicalSchemas) {
                currState = schema.turnOff();
            }

            this.currentState = State.DESPAIR;
            return this.currentState;
        }
        return this.currentState;
    }

    public boolean turn() {
        if (this.currentState == State.DESPAIR) {
            System.out.printf("[%s], being in state of [%s], turned with a spasm.\n", this.getTargetName(), this.currentState);
            return true;
        }
        System.out.printf("[%s], being in state of [%s], turned.\n", this.getTargetName(), this.currentState);
        return false;

    }

    @Override
    public Material getMaterial() {
        return Material.MIXED;
    }
}
