package task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TreeTest {

    private RedBlackTree rbt;

    @BeforeEach
    void init() {
        rbt = new RedBlackTree();
    }

    @Test
    public void simpleInsertTestOfRoot() {
        rbt.insert(1);
        Node node = rbt.getRoot();
        Assertions.assertEquals(node.getData(), 1);
    }

    @Test
    public void insertTwoElementsTest() {
        rbt.insert(2);
        rbt.insert(1);
        Assertions.assertEquals(rbt.inorder(), "1 2 ");
    }

    @Test
    public void insertTwoSameElementsTest() {
        rbt.insert(1);
        rbt.insert(1);
        Assertions.assertEquals(rbt.inorder(), "1 1 ");
    }

    @Test
    public void zeroElementTest() {
        Node node = rbt.gettNULL();
        Node root = rbt.getRoot();
        Assertions.assertEquals(node, root);
    }

    @Test
    public void deleteZeroElementTest(){
        Assertions.assertThrows(IllegalAccessException.class, () -> rbt.deleteNode(2), "Couldn't find key in the tree");
    }

    @Test
    public void insertNegativeElementsTest(){
        rbt.insert(-2);
        rbt.insert(-5);
        rbt.insert(2);
        rbt.insert(1);
        Assertions.assertEquals(rbt.inorder(), "-5 -2 1 2 ");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, -1, 1000, -1223})
    public void findOneInOneElementTest(Integer input){
        rbt.insert(input);
        Assertions.assertEquals(input, rbt.searchTree(input).getData());
    }

    @ParameterizedTest
    @ValueSource(ints = {42, 12, 61, 654})
    public void findOneInManyElementTest(Integer input){
        rbt.insert(42);
        rbt.insert(12);
        rbt.insert(54);
        rbt.insert(-123);
        rbt.insert(654);
        rbt.insert(512);
        rbt.insert(-324);
        rbt.insert(-12);
        rbt.insert(61);

        rbt.insert(58);
        rbt.insert(78);
        rbt.insert(-65);
        rbt.prettyPrint();
        System.out.println(rbt.inorder());
        Assertions.assertEquals(input, rbt.searchTree(input).getData());

    }

    @ParameterizedTest
    @ValueSource(ints = {421, 1, -42, 0})
    public void simpleDeleteOneElementTest(Integer input) throws IllegalAccessException {
        rbt.insert(input);
        rbt.deleteNode(input);

        Assertions.assertEquals(rbt.searchTree(input), rbt.gettNULL());
    }

    @ParameterizedTest
    @ValueSource(ints = {421, 1, -42, 0})
    public void simpleDeleteOneElementInTwosSameTest(Integer input) throws IllegalAccessException {
        rbt.insert(input);
        rbt.insert(input);
        rbt.deleteNode(input);

        Assertions.assertEquals(rbt.searchTree(input).getData(), input);
    }

    @ParameterizedTest
    @ValueSource(ints = {42, 12, 54, -123, 654, 512, -324, -12, 61, 58, 78, -65})
    public void findDeleteInManyElementTest(Integer input) throws IllegalAccessException {
        rbt.insert(42);
        rbt.insert(12);
        rbt.insert(54);
        rbt.insert(-123);
        rbt.insert(654);
        rbt.insert(512);
        rbt.insert(-324);
        rbt.insert(-12);
        rbt.insert(61);
        rbt.insert(58);
        rbt.insert(78);
        rbt.insert(-65);

        rbt.deleteNode(input);
        rbt.prettyPrint();
        System.out.println(rbt.inorder());

        Assertions.assertEquals(rbt.searchTree(input), rbt.gettNULL());

    }
}
