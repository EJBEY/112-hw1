package hw;

class Bag<T> implements IBag<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] items;
    private int size;

    public Bag() {
        this(DEFAULT_CAPACITY);
    }

    public Bag(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be positive");

        items = new Object[capacity];
        size = 0;
    }

    @Override
    public boolean add(T newEntry) {
        ensureCapacity();
        items[size] = newEntry;
        size++;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == items.length;
    }

	@Override
    @SuppressWarnings("unchecked")
    public T removeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
		T removedItem = (T) items[index];
        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        items[size - 1] = null;
        size--;
        return removedItem;
    }

    @Override
    public boolean remove(T anEntry) {
        int index = getIndexOf(anEntry);
        if (index != -1) {
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
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].equals(anEntry)) {
                frequency++;
            }
        }
        return frequency;
    }

    @Override
    public int getIndexOf(T anEntry) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(anEntry)) {
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
            items[i] = null;
        }
        size = 0;
    }

    @Override
    public void displayItems() {
        for (int i = 0; i < size; i++) {
            System.out.println(items[i]);
        }
    }

    @Override
    public int getCurrentSize() {
        return size;
    }

    @SuppressWarnings("unchecked")
	@Override
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        System.arraycopy(items, 0, array, 0, size);
        return array;
    }

    private void ensureCapacity() {
        if (isFull()) {
            Object[] newItems = new Object[items.length * 2];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
    }
}
