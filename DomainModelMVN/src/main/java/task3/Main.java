package task3;

import task3.abstractions.INamed;
import task3.enums.IdeaContent;
import task3.enums.Material;
import task3.enums.State;
import task3.implementations.Door;
import task3.implementations.Robot;
import task3.implementations.idea.TargetedIdea;

public class Main {
    public static void main(String[] args) {
        Robot marvin = new Robot("Marvin", 2);
        State watchindState = marvin.watch((INamed) () -> "She");
        State finalState = marvin.useSchemas(new TargetedIdea(IdeaContent.PHYSICAL_ABUSE, new Door(Material.WOOD)));
        boolean isSpasmed = marvin.turn();
        System.out.printf("Program result: Marvin's watching state was [%s], while the final state (after schemas work) is [%s], and he is [%s].\n", watchindState, finalState, isSpasmed ? "spasmed" : "not spasmed");
    }
}
