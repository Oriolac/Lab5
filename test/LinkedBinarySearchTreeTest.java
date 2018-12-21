import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedBinarySearchTreeTest<K, V> {

    @Test
    public void put() {
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(8, 8L);
        lbst = lbst.put(12, 12L);
        lbst = lbst.put(7, 7L);
        lbst = lbst.put(15, 15L);
        assertEquals("(7, 7)(8, 8)(12, 12)(15, 15)", lbst.toString());
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
    public void putEqualKey(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        LinkedBinarySearchTree<Integer, Long> lbst2 = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(7, 7L);
        lbst = lbst.put(4, 4L);
        lbst = lbst.put(1, 1L);
        lbst = lbst.put(3, 3L);
        lbst2 = lbst2.put(7, 7L);
        lbst2 = lbst2.put(4, 4L);
        lbst2 = lbst2.put(1, 1L);
        lbst2 = lbst2.put(3, 3L);
        lbst2 = lbst2.put(4, 4L);
        lbst2 = lbst2.put(7, 7L);
        lbst2 = lbst2.put(3, 3L);
        assertEquals(lbst.toString(), lbst2.toString());
    }

    @Test
    public void removeLittleKey(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(8, 8L);
        lbst = lbst.put(12, 12L);
        lbst = lbst.put(7, 7L);
        lbst = lbst.put(15, 15L);
        lbst = lbst.put(5, 5L);
        lbst = lbst.put(2, 2L);
        lbst = lbst.remove(5);
        lbst = lbst.remove(2);
        assertEquals("(7, 7)(8, 8)(12, 12)(15, 15)", lbst.toString());
    }

    @Test
    public void removeGreatestKey(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(8, 8L);
        lbst = lbst.put(12, 12L);
        lbst = lbst.put(7, 7L);
        lbst = lbst.put(15, 15L);
        lbst = lbst.put(5, 5L);
        lbst = lbst.put(2, 2L);
        lbst = lbst.remove(12);
        assertEquals("(2, 2)(5, 5)(7, 7)(8, 8)(15, 15)", lbst.toString());

    }


    @Test
    public void removeInexistentKey(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer,Long>(Comparator.naturalOrder());
        lbst = lbst.put(8, 8L);
        lbst = lbst.put(12, 12L);
        lbst = lbst.put(7, 7L);
        lbst = lbst.put(15, 15L);
        lbst = lbst.remove(3);
        assertEquals("(7, 7)(8, 8)(12, 12)(15, 15)", lbst.toString());
    }

    @Test
    public void removeEmptyBinarySearchTree(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst.remove(2);
        assertEquals("", lbst.toString());
    }

    @Test (expected = NullPointerException.class)
    public void removeNullKey(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(8, 8L);
        lbst = lbst.put(12, 12L);
        lbst = lbst.put(7, 7L);
        lbst = lbst.put(15, 15L);
        lbst = lbst.put(5, 5L);
        lbst = lbst.put(2, 2L);
        lbst = lbst.remove(null);
    }

    @Test
    public void removeBigBinarySearchTree(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(30, 30L);
        lbst = lbst.put(15, 15L);
        lbst = lbst.put(45, 45L);
        lbst = lbst.put(7, 7L);
        lbst = lbst.put(22, 22L);
        lbst = lbst.put(37, 37L);
        lbst = lbst.put(52, 52L);
        lbst = lbst.put(3, 3L);
        lbst = lbst.put(10, 10L);
        lbst = lbst.put(18, 18L);
        lbst = lbst.put(25, 25L);
        lbst = lbst.put(33, 33L);
        lbst = lbst.put(40, 40L);
        lbst = lbst.put(48, 48L);
        lbst = lbst.put(55, 55L);
        lbst = lbst.remove(45);
        lbst = lbst.remove(37);
        lbst = lbst.remove(22);
        lbst = lbst.remove(30);
        assertEquals("(3, 3)(7, 7)(10, 10)(15, 15)(18, 18)(25, 25)(33, 33)(40, 40)(48, 48)(52, 52)(55, 55)", lbst.toString());
    }

    @Test
    public void remove2(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(8, 8L);
        lbst = lbst.put(6, 6L);
        lbst = lbst.put(7, 7L);
        lbst = lbst.remove(8);
        assertEquals("(6, 6)(7, 7)", lbst.toString());
    }

    @Test
    public void remove3(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(8, 8L);
        lbst = lbst.put(6, 6L);
        lbst = lbst.put(3, 3L);
        lbst = lbst.put(5, 5L);
        lbst = lbst.remove(8);
        assertEquals("(3, 3)(5, 5)(6, 6)", lbst.toString());
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
        lbst = lbst.put(7, 7L);
        lbst = lbst.put(4, 4L);
        lbst = lbst.put(1, 1L);
        lbst = lbst.put(3, 3L);
        lbst = lbst.put(10, 10L);
        lbst = lbst.put(12, 12L);
        lbst = lbst.put(8, 8L);
        lbst = lbst.put(9, 9L);
        assertEquals(4L, (long) lbst.get(4));
        assertEquals(3L, (long) lbst.get(3));
        assertEquals(9L, (long) lbst.get(9));
        assertEquals(12L, (long) lbst.get(12));
        assertEquals(10L, (long) lbst.get(10));
    }

    @Test(expected = NullPointerException.class)
    public void getNullKey(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst = lbst.put(3, 3L);
        lbst = lbst.put(4, 4L);
        lbst = lbst.put(1, 1L);
        lbst.get(null);
    }

    @Test
    public void getEmptyTree(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst.get(6);
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
        assertEquals(InOrder.inOrder(lbst).toString(), expectedList.toString());
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
        ArrayList<Pair<Integer,Long>> pairs = new ArrayList<>();

        int[] param = new int[] {5,1,7,0,2,3,4,6,8};

        for(int i = 0; i < 9; i++){
            pairs.add(new Pair<>(i, (long)i ));

        }
        for(int i = 0; i < 9; i++){

            Pair<Integer, Long> pair = pairs.get(i);
            tree = tree.put(pair.first(), pair.second());
        }

        Iterator<Pair<Integer, Long>> it = tree.iterator();
        int i = 0;
        while(it.hasNext()){
            Pair<Integer,Long> actualPair = it.next();
            assertEquals(actualPair.toString(),pairs.get(i).toString());
            i++;
        }
    }

    @Test
    public void iterator2(){
        LinkedBinarySearchTree<Integer,Long> tree =  new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        ArrayList<Pair<Integer,Long>> pairs = new ArrayList<>();

        for(int i = 0; i < 9; i++){
            pairs.add(new Pair<>(i, (long) i));
            tree = tree.put(pairs.get(i).first(),pairs.get(i).second());
        }

        Iterator<Pair<Integer,Long>> it = tree.iterator();
        int i = 0;
        while(it.hasNext()){
            Pair<Integer,Long> actualPair = it.next();
            assertEquals(actualPair.toString(),pairs.get(i).toString());
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

    @Test(expected = NoSuchElementException.class)
    public void next(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        Iterator<Pair<Integer, Long>> it = lbst.iterator();
        it.next();
    }

    @Test(expected = NullPointerException.class)
    public void left(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst.left();
    }

    @Test(expected = NullPointerException.class)
    public void right(){
        LinkedBinarySearchTree<Integer, Long> lbst = new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        lbst.right();
    }
}