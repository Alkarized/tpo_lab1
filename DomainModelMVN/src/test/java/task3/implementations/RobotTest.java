package task3.implementations;

import task3.abstractions.INamed;
import task3.enums.IdeaContent;
import task3.enums.Material;
import task3.enums.State;
import task3.implementations.idea.Idea;
import task3.implementations.idea.TargetedIdea;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobotTest {

    Robot notZeroSchemas;
    Robot zeroSchemas;

    @BeforeEach
    void setupEach() {
        notZeroSchemas = new Robot("Rob", 1);
        zeroSchemas = new Robot("Rob", 0);
    }
    @Test
    void testWatchShe() {
        System.out.println("Test Robot.watch(new INamed(She))");
        Assertions.assertEquals(
                notZeroSchemas.watch((INamed) () -> "She"),
                State.COLD_CONTEMPT);
        Assertions.assertEquals(
                zeroSchemas.watch((INamed) () -> "She"),
                State.COLD_CONTEMPT);
    }

    @Test
    void testWatchNotShe() {
        System.out.println("Test Robot.watch(new INamed(...))");
        Assertions.assertEquals(
                notZeroSchemas.getCurrentState(),
                notZeroSchemas.watch((INamed) () -> "Not She"));
        Assertions.assertEquals(
                zeroSchemas.getCurrentState(),
                zeroSchemas.watch((INamed) () -> "Not She"));
    }


    @Test
    void testUseSchemasZeroSchemas() {
        System.out.println("Test Robot.useSchemas()[0 schemas]");
        Assertions.assertEquals(
                zeroSchemas.getCurrentState(),
                zeroSchemas.useSchemas(new TargetedIdea(IdeaContent.PHYSICAL_ABUSE, new Door(Material.MIXED))));
    }

    @Test
    void testUseSchemasNotShe() {
        System.out.println("Test Robot.useSchemas()[Not She]");
        Assertions.assertEquals(
                notZeroSchemas.getCurrentState(),
                notZeroSchemas.watch((INamed) () -> "Not She"));
        Assertions.assertEquals(
                notZeroSchemas.getCurrentState(),
                notZeroSchemas.useSchemas(new TargetedIdea(IdeaContent.PHYSICAL_ABUSE, new Door(Material.MIXED))));
    }

    @Test
    void  testUseSchemasNotPhysicalAbuse() {
        System.out.println("Test Robot.useSchemas()[Not PHYSICAL_ABUSE]");
        Assertions.assertEquals(
                notZeroSchemas.watch((INamed) () -> "She"),
                State.COLD_CONTEMPT);
        Assertions.assertEquals(
                notZeroSchemas.getCurrentState(),
                notZeroSchemas.useSchemas(new TargetedIdea(IdeaContent.HAPPINESS, new Door(Material.MIXED))));
    }

    @Test
    void testUseSchemasPhysicalAbuseNonTargeted() {
        System.out.println("Test Robot.useSchemas(new Idea(PHYSICAL_ABUSE))");
        Assertions.assertEquals(
                notZeroSchemas.watch((INamed) () -> "She"),
                State.COLD_CONTEMPT);
        Assertions.assertEquals(
                notZeroSchemas.useSchemas(new Idea(IdeaContent.PHYSICAL_ABUSE)),
                State.DOUBT);
    }

    @Test
    void testUseSchemasPhysicalAbuseTargeted() {
        System.out.println("Test Robot.useSchemas(new TargetedIdea(PHYSICAL_ABUSE))");
        Assertions.assertEquals(
                notZeroSchemas.watch((INamed) () -> "She"),
                State.COLD_CONTEMPT);
        Assertions.assertEquals(
                notZeroSchemas.useSchemas(new TargetedIdea(IdeaContent.PHYSICAL_ABUSE, new Door(Material.MIXED))),
                State.DESPAIR);
    }

    @Test
    void testTurnDespaired() {
        System.out.println("Test Robot.turn()[DESPAIR]");
        Assertions.assertEquals(
                notZeroSchemas.watch((INamed) () -> "She"),
                State.COLD_CONTEMPT);
        Assertions.assertEquals(
                notZeroSchemas.useSchemas(new TargetedIdea(IdeaContent.PHYSICAL_ABUSE, new Door(Material.MIXED))),
                State.DESPAIR);
        Assertions.assertTrue(notZeroSchemas.turn());
    }

    @Test
    void testTurnUndespaired() {
        System.out.println("Test Robot.turn()[Not DESPAIR]");
        Assertions.assertEquals(
                notZeroSchemas.watch((INamed) () -> "She"),
                State.COLD_CONTEMPT);
        Assertions.assertEquals(
                notZeroSchemas.useSchemas(new Idea(IdeaContent.PHYSICAL_ABUSE)),
                State.DOUBT);
        Assertions.assertFalse(notZeroSchemas.turn());
    }

}
