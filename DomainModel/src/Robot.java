public class Robot extends ANamed {

    private final Schema[] logicalSchemas;
    private State currentState;

    public Robot(String name, int numOfSchemas) {
        super(name);
        this.currentState = State.DEFAULT;
        this.logicalSchemas = new Schema[numOfSchemas];
        for (int i = 0; i < numOfSchemas; i++) {
            logicalSchemas[i] = new Schema();
        }
    }

    public String getName() {
        return super.getName();
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void watch(ITargetable target, State state) {
        setCurrentState(state);
        System.out.printf("[%s], being in state of [%s], watched after [%s].\n", this.getTargetName(), this.currentState, target.getTargetName());
    }

    public void useSchemas() {
        Door theDoor = new Door();
        Idea idea = new TargetedIdea("Physically abuse", theDoor);
        for (Schema schema : this.logicalSchemas) {
            schema.chirp(State.DISGUST);
        }
        for (Schema schema : this.logicalSchemas) {
            schema.manipulate(idea);
        }
    }
}
