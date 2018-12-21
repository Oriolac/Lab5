import java.util.NoSuchElementException;

public interface Stack<E> {
    boolean isEmpty();
    E top() throws NoSuchElementException;
    void pop() throws NoSuchElementException;
    void push(E e);
}
