import java.util.ArrayList;
import java.util.List;

public class InOrder {

    public static <K,V> List<Pair<K,V>> inOrder(LinkedBinarySearchTree<K,V> bt){
        ArrayList<Pair<K,V>> list = new ArrayList<>();
        Stack<LinkedBinarySearchTree> treeStack = new LinkedStack<LinkedBinarySearchTree>();
        Stack<Pair<K,V>> rootStack = new LinkedStack<>();
        treeStack.push(bt);
        while(!treeStack.isEmpty()){
            LinkedBinarySearchTree<K,V> current = treeStack.top();
            treeStack.pop();
            if(!current.isEmpty()){
                treeStack.push(current.right());
                rootStack.push(current.root());
                treeStack.push(current.left());
            } else if(!rootStack.isEmpty()){
                list.add(rootStack.top());
                rootStack.pop();
            }
        }
        return list;
    }

}
