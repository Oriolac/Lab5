import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E> {
    private Node<E> top;

    public LinkedStack() {
        this.top = null;
    }

    private static class Node<E>{
        private E elem;
        private Node<E> next;

        private Node(E elem, Node<E> next){
            this.elem = elem;
            this.next = next;
        }
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public E top() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException();
        return top.elem;
    }

    @Override
    public void pop() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException();
        this.top = top.next;
    }

    @Override
    public void push(E e) {
        this.top = new Node<>(e, top);
    }
}
