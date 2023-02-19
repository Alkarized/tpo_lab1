import Abstractions.INamed;
import Enums.IdeaContent;
import Enums.Material;
import Enums.State;
import Implementations.Door;
import Implementations.Robot;
import Implementations.Idea.TargetedIdea;

public class Main {
    public static void main(String[] args) {
        Robot marvin = new Robot("Marvin", 2);
        marvin.watch((INamed) () -> "She", State.COLD_CONTEMPT);
        marvin.useSchemas(new TargetedIdea(IdeaContent.PHYSICAL_ABUSE, new Door(Material.WOOD)));
    }
}
