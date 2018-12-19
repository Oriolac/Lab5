import java.util.ArrayList;
import java.util.List;

public class InOrder {

    public static <K,V> List<Pair<K,V>> inOrder(LinkedBinarySearchTree<K,V> bt){
        ArrayList<Pair<K,V>> list = new ArrayList<>();
        Stack<LinkedBinarySearchTree> stack = new LinkedStack<LinkedBinarySearchTree>();
        Stack<Pair<K,V>> rootStack = new LinkedStack<>();
        stack.push(bt);
        while(!stack.isEmpty()){
            LinkedBinarySearchTree<K,V> current = stack.top();
            stack.pop();
            if(!current.isEmpty()){
                stack.push(current.right());
                rootStack.push(current.root());
                stack.push(current.left());
            } else if(!rootStack.isEmpty()){
                list.add(rootStack.top());
                rootStack.pop();
            }
        }
        return list;
    }

}
