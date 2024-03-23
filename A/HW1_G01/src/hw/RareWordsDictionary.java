package hw;

class RareWordsDictionary<T extends Word> extends Bag<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] dictionary;
    private int size;

    @SuppressWarnings("unchecked")
	public RareWordsDictionary() {
        dictionary = (T[]) new Word[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(T newEntry) {
        ensureCapacity();
        dictionary[size] = newEntry;
        size++;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == dictionary.length;
    }

    @SuppressWarnings("unchecked")
	@Override
    public T removeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        T removedItem = (T) dictionary[index];
        for (int i = index; i < size - 1; i++) {
            dictionary[i] = dictionary[i + 1];
        }
        dictionary[size - 1] = null; // Clearing the last element
        size--;
        return removedItem;
    }

    @Override
    public boolean remove(T anEntry) {
        int index = getIndexOf(anEntry);
        if (index >= 0) {
            removeByIndex(index);
            return true;
        }
        return false;
    }

    @Override
    public T remove() {
        if (isEmpty())
            throw new IllegalStateException("Bag is empty");
        return removeByIndex(size - 1);
    }

    @Override
    public int getIndexOf(T anEntry) {
        for (int i = 0; i < size; i++) {
            if (dictionary[i].equals(anEntry)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T anEntry) {
        return getIndexOf(anEntry) != -1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            dictionary[i] = null;
        }
        size = 0;
    }

    @Override
    public void displayItems() {
        for (int i = 0; i < size; i++) {
            System.out.print(dictionary[i] + " ");
        }
        System.out.println();
    }
    
    @Override
    public int getCurrentSize() {
        return size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        T[] array = (T[]) new Word[size]; // Create an array of type T[]
        for (int i = 0; i < size; i++) {
            array[i] = (T) dictionary[i]; // Cast each element from Object to T
        }
        return array;
    }

	private void ensureCapacity() {
        if (isFull()) {
            Object[] newItems = new Object[dictionary.length * 2];
            System.arraycopy(dictionary, 0, newItems, 0, dictionary.length);
            dictionary = newItems;
        }
    }
}