package task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathTest {

    MathArc mathArc;
    double eps;

    @BeforeAll
    public void init(){
        mathArc = new MathArc();
        eps = 0.1;
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2, -300, -1_000_000_000})
    void outOfBoundsNegativeTest(final double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {2, 300, 1_000_000_000})
    void outOfBoundsPositiveTest(final double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 1})
    void onEdgesTest(final double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps*6);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.01, 0.001})
    void smallNumberPositiveTest(final double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1, -0.01, -0.001})
    void smallNumberNegativeTest(final double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1 - 0.1, 1 - 0.01, 1 - 0.001})
    void nearPositiveEdgeInsideTest(final double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps*4);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1 + 0.1, -1 + 0.01, -1 + 0.001})
    void nearNegativeEdgeInsideTest(final double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps*4);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1 + 0.1, 1 + 0.01, 1 + 0.001})
    void nearPositiveEdgeOutsideTest(final double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1 - 0.1, -1 - 0.01, -1 - 0.001})
    void nearNegativeEdgeOutsideTest(final double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.0001, 0.000_01})
    void zeroNumberTest(final double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }
}
