package task3;

import task3.Abstractions.INamed;
import task3.Enums.IdeaContent;
import task3.Enums.Material;
import task3.Enums.State;
import task3.Implementations.Door;
import task3.Implementations.Robot;
import task3.Implementations.Idea.TargetedIdea;

public class Main {
    public static void main(String[] args) {
        Robot marvin = new Robot("Marvin", 2);
        State watchindState = marvin.watch((INamed) () -> "She");
        State finalState = marvin.useSchemas(new TargetedIdea(IdeaContent.PHYSICAL_ABUSE, new Door(Material.WOOD)));
        boolean isSpasmed = marvin.turn();
        System.out.printf("Program result: Marvin's watching state was [%s], while the final state (after schemas work) is [%s], and he is [%s].\n", watchindState, finalState, isSpasmed ? "spasmed" : "not spasmed");
    }
}
