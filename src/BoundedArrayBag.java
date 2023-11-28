import java.util.Arrays;
import java.util.NoSuchElementException;

public class BoundedArrayBag<T> implements Bag<T> {
    private T[] items;
    private int size;

    public BoundedArrayBag(int capacity) {
        items = (T[])new Object[capacity];
        size = 0;
    }
    public int size() {
        return size;
    }

    public void add(T elem) {
        assert elem != null;
        if(size == items.length) {
            throw new IllegalStateException();
        }
        items[size] = elem;
        size++;
    }

    public boolean remove(T elem) {
        assert elem != null;
        assert size > 0;
        if(contains(elem)) {
            for(int i=indexOf(elem); i<size-1; i++) {
                items[i] = items[i+1];
            }
            items[size-1] = null;
            size--;
            return true;
        }
        return false;
    }

    private int indexOf(T elem) {
        for(int i=0; i<size; i++) {
            if(items[i].equals(elem)) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    public boolean contains(T elem) {
        assert elem != null;
        for(int i=0; i<size; i++) {
            if(items[i].equals(elem)) {
                return true;
            }
        }
        return false;
    }

    public int frequencyOf(T elem) {
        assert elem != null;
        int ret = 0;
        for(int i=0; i<size; i++) {
            if(items[i].equals(elem)) {
                ret++;
            }
        }
        return ret;
    }

    public T[] toArray() {
        return Arrays.copyOf(items, size);
    }
}
