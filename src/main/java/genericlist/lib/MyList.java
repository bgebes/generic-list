package genericlist.lib;

import java.util.Arrays;
import java.util.Objects;

public class MyList<T> implements ListArchitecture<T> {
    private T[] array;
    private int capacity = 10;
    private int cursor = -1;

    public MyList() {
        array = (T[]) new Object[capacity];
    }

    public MyList(int capacity) {
        array = (T[]) new Object[this.capacity = capacity];
    }

    @Override
    public int size() {
        int size = 0;

        for (int i = 0; i < capacity; i++) {
            if (array[i] != null) size++;
        }

        return size;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public void incrementCapacity() {
        int latestCapacity = capacity * 2;
        T[] t = (T[]) new Object[latestCapacity];

        for (int i = 0; i < capacity; i++) {
            t[i] = array[i];
        }

        this.array = t;
        this.capacity = latestCapacity;
    }

    @Override
    public T add(T data) {
        if (++cursor >= capacity) {
            incrementCapacity();
        }

        return array[cursor] = data;
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public T remove(int index) {
        T data = array[index];
        array[index] = null;
        array = toArray();

        return data;
    }

    @Override
    public T set(int index, T data) {
        return array[index] = data;
    }

    @Override
    public int indexOf(T data) {
        int index = -1;

        for (int i = 0; i < capacity; i++) {
            if (array[i] == data) {
                index = i;
                break;
            }
        }

        return index;
    }

    @Override
    public int lastIndexOf(T data) {
        int index = -1;

        for (int i = 0; i < capacity; i++) {
            if (array[i] == data) index = i;
        }

        return index;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = true;

        for (int i = 0; i < capacity; i++) {
            if (array[i] != null) isEmpty = false;
        }

        return isEmpty;
    }

    @Override
    public T[] toArray() {
        capacity = roundSize();
        T[] t = (T[]) new Object[capacity];
        int index = -1;

        for (int i = 0; i < capacity; i++) {
            if (array[i] != null) t[++index] = array[i];
        }

        for (int i = index + 1; i < capacity; i++) {
            t[i] = null;
        }

        return t;
    }

    @Override
    public int roundSize() {
        return (((int) size() / 10) * 10) + 10;
    }

    @Override
    public int clear() {
        int len = size();

        this.capacity = 10;
        this.array = (T[]) new Object[capacity];

        return len;
    }

    @Override
    public MyList<T> subList(int start, int finish) {
        MyList<T> subList = new MyList<>();

        for (int i = start; i <= finish; i++) {
            subList.add(array[i]);
        }

        return subList;
    }

    @Override
    public boolean contains(T data) {
        return indexOf(data) != -1;
    }

    @Override
    public String toString() {
        array = toArray();

        StringBuilder builder = new StringBuilder();

        builder.append("[");

        int len = size();
        for (int i = 0; i < len; i++) {
            T data = array[i];

            builder.append(i == len - 1 ? data : data + ",");
        }
        builder.append("]");

        return builder.toString();
    }
}
