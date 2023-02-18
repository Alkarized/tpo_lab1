public class Schema implements ITargetable {
    private State currentState;
    private ITargetable lastManipulatedObject;

    public Schema() {
        this.currentState = State.DEFAULT;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void chirp(State state) {
        setCurrentState(state);
        System.out.printf("[%s], being in state of [%s], chirped.\n", this.getTargetName(), this.currentState);
    }

    public void manipulate(ITargetable target) {
        this.lastManipulatedObject = target;
        System.out.printf("[%s], being in state of [%s], manipulated the [%s].\n", this.getTargetName(), this.currentState, this.lastManipulatedObject.getTargetName());
    }

    @Override
    public String getTargetName() {
        return "The schema[" + hashCode() + "]";
    }
}
