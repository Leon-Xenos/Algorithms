package DataStructures;

import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
    private final List<T> list = new ArrayList<>();

    public void push(T value) {
        list.add(value);
    }

    public T poll() {
        return list.remove(list.size() - 1);
    }

    public T peek() {
        return list.get(list.size() - 1);
    }

    public int size() {
        return list.size();
    }
}
