import java.util.Arrays;
import java.util.NoSuchElementException;

public class DynamicArrayBag<T> implements Bag<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private T[] items;
    private int size;

    private void assertInv() {
        for (int i = 0; i < size; i++) {
            assert items[i] != null;
        }
        for (int i = size; i < items.length; i++) {
            assert items[i] == null;
        }
    }

    public DynamicArrayBag() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        assertInv();
    }

    public int size() {
        return size;
    }

    public void add(T elem) {
        assert elem != null;
        if(size==items.length) {
            T[] biggerItems = Arrays.copyOf(items, 2*items.length);
            items = biggerItems;
        }
        items[size+1] = elem;
        size++;
        assertInv();
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
            assertInv();
            return true;
        }
        assertInv();
        return false;
    }

    private int indexOf(T elem) {
        for(int i=0; i<size; i++) {
            if(items[i].equals(elem)) {
                assertInv();
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    public boolean contains(T elem) {
        assert elem != null;
        for(int i=0; i<size; i++) {
            if(items[i].equals(elem)) {
                assertInv();
                return true;
            }
        }
        assertInv();
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
