package Lab3;

import java.util.EmptyStackException;

public class MyStack<E> {
    private StackNode<E> top;

    public MyStack() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(E val) {
        top = new StackNode<>(val, null, top);
    }

    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        E val = top.element;
        top = top.next;
        return val;
    }

    public static class StackNode<E> {
        E element;
        StackNode<E> prev;
        StackNode<E> next;

        public StackNode(E element, StackNode<E> prev, StackNode<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }
}
