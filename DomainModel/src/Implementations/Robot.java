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

    public void watch(ITargetable target, State state) {
        this.currentState = state;
        System.out.printf("[%s], being in state of [%s], watched after [%s].\n", this.getTargetName(), this.currentState, target.getTargetName());
    }

    public void useSchemas(Idea idea) {
        if (this.logicalSchemas.length == 0) {
            this.currentState = State.DEFAULT;
            return;
        }
        if (idea.getContent() == IdeaContent.PHYSICAL_ABUSE) {
            for (Schema schema : this.logicalSchemas) {
                schema.chirp(State.DISGUST);
            }
            for (Schema schema : this.logicalSchemas) {
                schema.manipulate(idea);
            }
            for (Schema schema : this.logicalSchemas) {
                schema.click();
                schema.setCurrentState(State.DOUBT);
            }
            for (Schema schema : this.logicalSchemas) {
                schema.speak();
            }
            if (!(idea instanceof TargetedIdea && ((TargetedIdea) idea).getTarget() instanceof IMaterialized)) {
                this.currentState = this.logicalSchemas[0].getCurrentState();
                return;
            }
            for (Schema schema : this.logicalSchemas) {
                schema.compareMaterials(((IMaterialized) ((TargetedIdea) idea).getTarget()).getMaterial(), new HumanBrain().getMaterial());
                schema.setCurrentState(State.FUN);
            }

            this.currentState = State.DESPAIR;
            return;
        }
        this.currentState = State.DEFAULT;
    }

    @Override
    public Material getMaterial() {
        return Material.MIXED;
    }
}
