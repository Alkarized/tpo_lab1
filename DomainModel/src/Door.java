public class Door implements ITargetable{

    @Override
    public String getTargetName() {
        return "The Door[" + this.hashCode() + "]";
    }
}
