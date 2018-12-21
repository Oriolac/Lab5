import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import static org.junit.Assert.*;

public class LinkedBinarySearchTreeTest<K, V> {

    @Test
    public void put() {
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(8, 8L);
        lbst = lbst.put(12, 12L);
        lbst = lbst.put(7, 7L);
        lbst = lbst.put(15, 15L);
        assertEquals("(7, 7L), (8, 8L), (12, 12L), (15, 15L)", lbst.toString());
    }

    @Test (expected = NullPointerException.class)
    public void putNullKey(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst.put(null, 8L);
    }

    @Test(expected = NullPointerException.class)
    public void putNullValue(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst.put(6, null);
    }

    @Test (expected = NullPointerException.class)
    public void putNullKeyAndNullValue(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst.put(null, null);
    }

    @Test
    public void remove(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst.put(8, 8L);
        lbst.put(12, 12L);
        lbst.put(7, 7L);
        lbst.put(15, 15L);
        lbst.put(5, 5L);
        lbst.put(2, 2L);
        lbst.remove(5);
        lbst.remove(2);
        assertEquals("(7, 7L), (8, 8L), (12, 12L), (15, 15L)", lbst.toString());
    }

    @Test (expected = NullPointerException.class)
    public void removeNullKey(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst.put(8, 8L);
        lbst.put(12, 12L);
        lbst.put(7, 7L);
        lbst.put(15, 15L);
        lbst.put(5, 5L);
        lbst.put(2, 2L);
        lbst.remove(null);
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
        assertFalse(lbst.containsKey(5));
    }

    @Test
    public void get(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(3, 3L);
        lbst = lbst.put(4, 4L);
        lbst = lbst.put(1, 1L);
        assertEquals(4L, (long) lbst.get(4));
    }

    @Test
    public void getNullKey(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(3, 3L);
        lbst = lbst.put(4, 4L);
        lbst = lbst.put(1, 1L);
        assertNull(lbst.get(null));
    }

    @Test
    public void inOrder() {
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        ArrayList<Pair<Integer, Long>> expectedList = new ArrayList<>();
        long l = 0;
        for (int i = 0; i < 7; i++) {
            expectedList.add(new Pair<>(i, l));
            l++;
        }
        lbst = lbst.put(3, 3L);
        lbst = lbst.put(4, 4L);
        lbst = lbst.put(1, 1L);
        lbst = lbst.put(0, 0L);
        lbst = lbst.put(2, 2L);
        lbst = lbst.put(6, 6L);
        lbst = lbst.put(5, 5L);
        assertEquals(InOrder.inOrder(lbst), expectedList);
    }

    @Test
    public void hasNextIterator(){
        LinkedBinarySearchTree<Integer,Long> tree = new LinkedBinarySearchTree<Integer,Long>(Comparator.naturalOrder());
        Iterator it = tree.iterator();
        assertFalse(it.hasNext());
        tree = tree.put(3,3L);
        it = tree.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void iterator(){
        LinkedBinarySearchTree<Integer,Long> tree =  new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        Pair<Integer,Long>[] pairs = new Pair[9];
        int[] param = new int[] {5,1,7,0,2,3,4,6,8};

        for(int i = 0; i < pairs.length; i++){
            pairs[i] = new Pair<>(i, (long) i);
        }
        for(int i = 0; i < pairs.length; i++){
            tree = tree.put(pairs[param[i]].first(), pairs[param[i]].second());
        }

        Iterator<Pair<Integer, Long>> it = tree.iterator();
        int i = 0;
        while(it.hasNext()){
            Pair<Integer,Long> actualPair = it.next();
            assertEquals(actualPair.toString(),pairs[i].toString());
            i++;
        }
    }

    @Test
    public void iterator2(){
        LinkedBinarySearchTree<Integer,Long> tree =  new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        Pair<Integer,Long> pairs[] = new Pair[9];
        for(int i = 0; i < pairs.length; i++){
            pairs[i] = new Pair<>(i, (long) i);
            tree = tree.put(pairs[i].first(), pairs[i].second());
        }

        Iterator<Pair<Integer,Long>> it = tree.iterator();
        int i = 0;
        while(it.hasNext()){
            Pair<Integer, Long> actualPair = it.next();
            assertEquals(actualPair.toString(), pairs[i].toString());
            i++;
        }


    }

    @Test
    public void toStringTest(){
        LinkedBinarySearchTree<Integer,Long> tree =  new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        tree = tree.put(8, 8L);
        tree = tree.put(4, 4L);
        tree = tree.put(9, 9L);
        tree = tree.put(7, 7L);
        tree = tree.put(6, 6L);

        assertEquals("(4, 4)(6, 6)(7, 7)(8, 8)(9, 9)", tree.toString());
    }
}