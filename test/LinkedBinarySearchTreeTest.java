import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class LinkedBinarySearchTreeTest<K, V> {

    public int numException = 0;

    @Test
    public void put() {
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        LinkedBinarySearchTree<Integer, Long> expectedLbst = lbst;
        try {
            lbst.put(3, null);
        } catch(NullPointerException ex){
            System.out.println("Catch exception error: " + numException);
        }
        assertEquals(expectedLbst.toString(), lbst.toString());
        lbst = lbst.put(3, 2L);
        assertTrue(lbst.containsKey(3));

    }

    @Test
    public void isEmpty() {
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        assertTrue(lbst.isEmpty());
        lbst = lbst.put(3, 3L);
        assertFalse(lbst.isEmpty());
    }

    @Test
    public void containsKey() {
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        assertFalse(lbst.containsKey(null));
        lbst = lbst.put(3, 3L);
        assertTrue(lbst.containsKey(3));
        assertFalse(lbst.containsKey(2));
        lbst = lbst.put(0, 0L);
        lbst = lbst.put(4, 4L);
        assertTrue(lbst.containsKey(3));
        assertTrue(lbst.containsKey(0));
        assertTrue(lbst.containsKey(4));
        try{
            lbst = lbst.put(6, null);
        } catch(NullPointerException ex){

        }
        assertFalse(lbst.containsKey(6));
    }



    @Test
    public void inOrder() {
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(3,3L);
        lbst = lbst.put(4, 4L);
        lbst = lbst.put(1, 1L);
        lbst = lbst.put(0, 0L);
        lbst = lbst.put(2,2L);
        lbst = lbst.put(6,6L);
        lbst = lbst.put(5,5L);
        System.out.println(InOrder.inOrder(lbst).toString());
        System.out.println("Hola");
    }

}