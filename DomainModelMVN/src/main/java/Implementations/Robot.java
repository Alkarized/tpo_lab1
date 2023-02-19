package Implementations;

import Abstractions.IMaterialized;
import Abstractions.INamed;
import Abstractions.ITargetable;
import Enums.IdeaContent;
import Enums.Material;
import Enums.State;
import Implementations.Idea.Idea;
import Implementations.Idea.TargetedIdea;

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
        } else {
            this.currentState = State.DEFAULT;
        }
        System.out.printf("[%s], being in state of [%s], watched after [%s].\n", this.getTargetName(), this.currentState, target.getTargetName());
        return this.currentState;
    }

    public State useSchemas(Idea idea) {
        if (this.logicalSchemas.length == 0) {
            this.currentState = State.DEFAULT;
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
        this.currentState = State.DEFAULT;
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
