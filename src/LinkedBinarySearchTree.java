import java.util.*;

public class LinkedBinarySearchTree<K, V> implements BinarySearchTree<K, V>, BinaryTree<Pair<K, V>>, Iterable<Pair<K, V>> {

    private final Node<K, V> root;
    private final Comparator<K> comparator;

    @Override
    public Iterator<Pair<K, V>> iterator(){
        return new LinkedBinarySearchTreeIterator();
    }

    private class LinkedBinarySearchTreeIterator implements Iterator<Pair<K,V>>{

        private Stack<Node<K,V>> stack;

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Pair<K, V> next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            } else {

                Node<K,V> current = stack.top();
                stack.pop();

                if(current.right != null){
                    Node<K, V> next = current.right;
                    stack.push(next);
                    next = next.left;
                    while(next != null){
                        stack.push(next);
                        next = next.left;
                    }
                }
                return new Pair<>(current.key, current.value);
            }
        }


        public LinkedBinarySearchTreeIterator(){
            stack = new LinkedStack<>();
            Node<K,V> next = root;
            while(next != null){
                stack.push(next);
                next = next.left;
            }
        }


    }

    private static class Node<K, V> {
        private final K key;
        private final V value;
        private final Node<K, V> left;
        private final Node<K, V> right;

        private Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        private Node(K key, V value) {
            this(key, value, null, null);
        }
    }

    public LinkedBinarySearchTree(Comparator<K> comparator) {
        this(comparator, null);
    }

    private LinkedBinarySearchTree(Comparator<K> comparator, Node<K, V> root) {
        this.comparator = comparator;
        this.root = root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Pair<K, V> root() {
        return new Pair<>(root.key, root.value);
    }

    @Override
    public LinkedBinarySearchTree<K, V> left() throws NullPointerException{
        if(root == null)
            throw new NullPointerException();
        return new LinkedBinarySearchTree<>(this.comparator, root.left);
    }

    @Override
    public LinkedBinarySearchTree<K, V> right() throws NullPointerException{
        if(root == null)
            throw new NullPointerException();
        return new LinkedBinarySearchTree<>(this.comparator, root.right);
    }


    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * @param key, it is used to find the value it is associated with.
     * @return the value of the key or null if the key is not in the tree.
     */
    @Override
    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, Node<K, V> node) {
        if (node == null) { //Cas simple 1
            return null;
        } else if (comparator.compare(key, node.key) == 0) {    // Cas simple 2
            return node.value;
        }else if (comparator.compare(key, node.key) > 0){   // Cas recursiu 1
            return get(key, node.right);
        }else{  // Cas recursiu 2
            return get(key, node.left);
        }
    }

    @Override
    public LinkedBinarySearchTree<K, V> put(K key, V value) throws NullPointerException {
        if (key == null || value == null) {
            throw new NullPointerException();
        }
        Node<K, V> newRoot = newNodeOfTheBranch(key, value, this.root);
        return new LinkedBinarySearchTree<>(comparator, newRoot);
    }

    private Node<K, V> newNodeOfTheBranch(K key, V value, Node<K, V> actualNode) {
        if(actualNode == null){ //Cas simple 1
            return new Node<>(key, value);
        } else if(comparator.compare(key, actualNode.key) == 0){ //Cas simple 2
            return new Node<>(key, value, actualNode.left, actualNode.right);
        } else if (comparator.compare(key, actualNode.key) < 0){ //Casos recursius
            return new Node<>(actualNode.key, actualNode.value, newNodeOfTheBranch(key, value, actualNode.left), actualNode.right);
        } else {
            return new Node<>(actualNode.key, actualNode.value, actualNode.left, newNodeOfTheBranch(key, value, actualNode.right));
        }
    }

    @Override
    public LinkedBinarySearchTree<K, V> remove(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        try {
            Node<K, V> newRoot = remove(key, root);
            return new LinkedBinarySearchTree<>(comparator, newRoot);
        } catch (NoNodeException e) {
            return this;
        }
    }

    private Node<K,V> remove(K key, Node<K,V> node) throws NoNodeException {
        if(node == null){
            throw new NoNodeException();
        } else if(comparator.compare(key, node.key) == 0){
            if(node.right == null && node.left == null){
                return null;
            } else if(node.right == null){
                return mesGran(node, node.left);
            } else {
                return mesPetit(node, node.right);
            }
        } else { //Cas recursiu
            if(comparator.compare(key, node.key) > 0){
                return new Node<>(node.key, node.value, node.left, remove(key, node.right));
            } else {
                return new Node<>(node.key, node.value, remove(key, node.left), node.right);
            }
        }
    }

    //Busca el node més petit
    private Node<K,V> mesPetit(Node<K,V> nodeAEliminar, Node<K,V> actualNode) {
        if(actualNode.left == null){
            return new Node<>(actualNode.key, actualNode.value, nodeAEliminar.left, removeMesPetit(nodeAEliminar.right));
        }
        //Cas Recursiu
        return mesPetit(nodeAEliminar, actualNode.left);
    }

    //
    private Node removeMesPetit(Node<K,V> node) {
        if(node.left == null){
            return node.right;
        } else { //Cas Recursiu
            return new Node<K,V>(node.key, node.value, removeMesPetit(node.left), node.right);
        }
    }


    private Node<K,V> mesGran(Node<K,V> nodeAEliminar, Node<K,V> actualNode) {
        if(actualNode.right == null){
            return new Node(actualNode.key, actualNode.value, removeMesGran(nodeAEliminar.left), nodeAEliminar.right);
        }
        //Cas Recursiu
        return mesGran(nodeAEliminar, actualNode.right);
    }

    private Node removeMesGran(Node<K, V> node) {
        if(node.right == null){
            return node.left;
        } else { //Cas Recursiu
            return new Node<K,V>(node.key, node.value, node.left, removeMesGran(node.right));
        }
    }

    @Override
    public String toString(){
        return inOrderString();

    }

    private String inOrderString() {
        String str = new String();
        Iterator<Pair<K,V>> it = iterator();
        while(it.hasNext()){
            Pair pair = it.next();
            str += "(" + pair.first().toString() + ", " + pair.second().toString() + ")";
        }
        return str;
    }
}