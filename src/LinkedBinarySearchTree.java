import java.util.Comparator;

public class LinkedBinarySearchTree<K, V> implements BinarySearchTree<K, V> {

    private final Node<K, V> root;
    private final Comparator<K> comparator;

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

        public Node(K key, V value) {
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
    public boolean containsKey(K key) {
        return isKeyInNodeOrDeeperLevels(key, root);
    }

    private boolean isKeyInNodeOrDeeperLevels(K key, Node<K, V> node) {
        if (node == null) {   // 1r Cas Simple
            return false;
        } else if (comparator.compare(key, node.key) == 0) { // 2n Cas Simple
            return true;
        } else {    // Cas recursiu
            if (comparator.compare(key, node.key) < 0) {  //La clau pot estar en
                return isKeyInNodeOrDeeperLevels(key, node.left);
            } else {
                return isKeyInNodeOrDeeperLevels(key, node.right);
            }
        }
    }

    /**
     * @param key
     * @return the value of the key or null if the key is not in the tree.
     */
    @Override
    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, Node<K, V> root) {
        if (root == null) {
            return null;
        }
        switch (comparator.compare(key, root.key)) {
            case 0:
                return root.value;
            case 1:
                return get(key, root.right);
            default:
                return get(key, root.left);
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
        if(actualNode == null){
            return new Node<K,V>(key, value);
        }
        if (comparator.compare(key, root.key) < 0){
            return new Node<K, V>(key, value, newNodeOfTheBranch(key, value, root.left), root.right);
        } else {
            return new Node<K, V>(key, value, root.left, newNodeOfTheBranch(key, value, root.right));
        }
    }

    @Override
    public LinkedBinarySearchTree<K, V> remove(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        Node<K, V> newRoot = removeNodeOfTheBranch(key, this.root);
        return new LinkedBinarySearchTree<>(comparator, newRoot);
    }

    private Node<K, V> removeNodeOfTheBranch(K key, Node<K, V> root) {
        if(root == null){
            return null;
        } else if(comparator.compare(key, root.key)== 0){
            return null;
        } else {
            if(comparator.compare(key, root.key) < 0){
                return removeNodeOfTheBranch(key, root.left);
            } else {
                return removeNodeOfTheBranch(key, root.right);
            }
        }
    }
}
