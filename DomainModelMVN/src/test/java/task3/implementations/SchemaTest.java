package task3.implementations;

import task3.enums.Material;
import task3.enums.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SchemaTest {

    Schema schema;

    @BeforeEach
    void setupEach() {
        schema = new Schema();
    }

    @Test
    void testChirpDefault() {
        System.out.println("Test Schema.chirp()[DEFAULT]");
        Assertions.assertEquals(schema.chirp(), State.DISGUST);
    }

    @Test
    void testChirpNotDeafult() {
        System.out.println("Test Schema.chirp()[Not DEFAULT]");
        Assertions.assertEquals(schema.chirp(), State.DISGUST);
        Assertions.assertEquals(schema.getCurrentState(), schema.chirp());
    }

    @Test
    void testClickDisgust() {
        System.out.println("Test Schema.click()[DISGUST]");
        Assertions.assertEquals(schema.chirp(), State.DISGUST);
        Assertions.assertEquals(schema.click(), State.DOUBT);
    }

    @Test
    void testClickNotDisgust() {
        System.out.println("Test Schema.click()[Not DISGUST]");
        Assertions.assertEquals(schema.chirp(), State.DISGUST);
        Assertions.assertEquals(schema.click(), State.DOUBT);
        Assertions.assertEquals(schema.getCurrentState(), schema.click());
    }

    @Test
    void testCompareMaterialsDoubt() {
        System.out.println("Test Schema.compareMaterials()[DOUBT]");
        Assertions.assertEquals(schema.chirp(), State.DISGUST);
        Assertions.assertEquals(schema.click(), State.DOUBT);
        Assertions.assertEquals(schema.compareMaterials(Material.METAL, Material.METAL), State.FUN);
    }

    @Test
    void testCompareMaterialsNotDoubt() {
        System.out.println("Test Schema.compareMaterials()[Not DOUBT]");
        Assertions.assertEquals(schema.chirp(), State.DISGUST);
        Assertions.assertEquals(schema.click(), State.DOUBT);
        Assertions.assertEquals(schema.compareMaterials(Material.METAL, Material.METAL), State.FUN);
        Assertions.assertEquals(schema.getCurrentState(), schema.compareMaterials(Material.METAL, Material.METAL));
    }

    @Test
    void testCheckHydrogenFun() {
        System.out.println("Test Schema.checkHydrogen()[FUN]");
        Assertions.assertEquals(schema.chirp(), State.DISGUST);
        Assertions.assertEquals(schema.click(), State.DOUBT);
        Assertions.assertEquals(schema.compareMaterials(Material.METAL, Material.METAL), State.FUN);
        Assertions.assertEquals(schema.checkHydrogen(), State.BORED);
    }

    @Test
    void testCheckHydrogenNotFun() {
        System.out.println("Test Schema.checkHydrogen()[Not FUN]");
        Assertions.assertEquals(schema.chirp(), State.DISGUST);
        Assertions.assertEquals(schema.click(), State.DOUBT);
        Assertions.assertEquals(schema.compareMaterials(Material.METAL, Material.METAL), State.FUN);
        Assertions.assertEquals(schema.checkHydrogen(), State.BORED);
        Assertions.assertEquals(schema.getCurrentState(), schema.checkHydrogen());
    }

    @Test
    void testTurnOff() {
        System.out.println("Test Schema.turnOff()");
        Assertions.assertEquals(schema.turnOff(), State.DEFAULT);
    }

}
