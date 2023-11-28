interface Bag<T> {
    int size();

    void add(T elem);

    boolean remove(T elem);

    boolean contains(T elem);

    int frequencyOf(T elem);

    T[] toArray();
}
