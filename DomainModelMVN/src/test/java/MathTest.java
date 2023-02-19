import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import task1.MathArc;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathTest {

    MathArc mathArc;
    double eps;

    @BeforeAll
    public void init(){
        mathArc = new MathArc();
        eps = 0.1;
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2, -300, -1000000000})
    public void outOfBoundsNegativeTest(double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {2, 300, 1000000000})
    public void outOfBoundsPositiveTest(double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 1})
    public void onEdgesTest(double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps*6);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.01, 0.001})
    public void smallNumberPositiveTest(double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1, -0.01, -0.001})
    public void smallNumberNegativeTest(double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1 - 0.1, 1 - 0.01, 1 - 0.001})
    public void nearPositiveEdgeInsideTest(double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps*4);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1 + 0.1, -1 + 0.01, -1 + 0.001})
    public void nearNegativeEdgeInsideTest(double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps*4);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1 + 0.1, 1 + 0.01, 1 + 0.001})
    public void nearPositiveEdgeOutsideTest(double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1 - 0.1, -1 - 0.01, -1 - 0.001})
    public void nearNegativeEdgeOutsideTest(double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.0001, 0.00001})
    public void zeroNumberTest(double input){
        Assertions.assertEquals(Math.asin(input), mathArc.arcsin(input), eps);
    }
}
