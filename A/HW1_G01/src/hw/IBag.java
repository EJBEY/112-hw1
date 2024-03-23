package hw;

public interface IBag<T>{
    boolean add(T newEntry);
    boolean isEmpty();
    boolean isFull();
    T removeByIndex(int index);
    boolean remove(T anEntry);
    T remove();
    int getFrequencyOf(T anEntry);
    int getIndexOf(T anEntry);
    boolean contains(T anEntry);
    void clear();
    void displayItems();
    int getCurrentSize();
    T[] toArray();
}