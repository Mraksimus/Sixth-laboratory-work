package dev.mraksimus;

public class Stack<T> {

    private T[] data;
    private int size;

    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public void push(T element) {
        if (size == data.length) {
            throw new IllegalStateException("Стек переполнен");
        }
        data[size++] = element;
    }

    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("Стек пуст");
        }
        T element = data[--size];
        data[size] = null;
        return element;
    }

    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("Стек пуст");
        }
        return data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

}
