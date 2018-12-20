import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class LinkedBinarySearchTreeTest<K, V> {

    @Test
    public void iterator(){
        LinkedBinarySearchTree<Integer,Long> tree =  new LinkedBinarySearchTree<Integer, Long>(Comparator.naturalOrder());
        Pair<Integer,Long> pairs[] = new Pair[9];
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
        }

        Iterator<Pair<Integer,Long>> it = tree.iterator();
        int i = 0;
        while(it.hasNext()){
            Pair<Integer, Long> actualPair = it.next();
            assertEquals(actualPair.toString(), pairs[i].toString());
            i++;
        }

    }
}