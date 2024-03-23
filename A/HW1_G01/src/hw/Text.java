package hw;

class Text<T extends Word> extends Bag<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] words;
    private int size;

    @SuppressWarnings("unchecked")
	public Text() {
        words = (T[]) new Word[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(T newEntry) {
        ensureCapacity();
        words[size] = newEntry;
        size++;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == words.length;
    }

    @SuppressWarnings("unchecked")
	@Override
    public T removeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        T removedItem = (T) words[index];
        for (int i = index; i < size - 1; i++) {
            words[i] = words[i + 1];
        }
        words[size - 1] = null;
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
        if (isEmpty()) {
            throw new IllegalStateException("Bag is empty");
        }
        return removeByIndex(size - 1);
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        for (int i = 0; i < size; i++) {
            if (words[i].equals(anEntry)) {
                frequency++;
            }
        }
        return frequency;
    }
    
    @Override
    public int getIndexOf(T anEntry) {
        for (int i = 0; i < size; i++) {
            if (words[i].equals(anEntry)) {
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
            words[i] = null;
        }
        size = 0;
    }
    
    @Override
    public void displayItems() {
        for (int i = 0; i < size; i++) {
            System.out.print(words[i] + " ");
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
        T[] array = (T[]) new Word[size];
        System.arraycopy(words, 0, array, 0, size);
        return array;
    }
    
    public void separate(Dictionary<T> dictionary, RareWordsDictionary<T> rareWordsDictionary,
                         FrequentWordsDictionary<T> frequentWordsDictionary,
                         MostFrequentWordsDictionary<T> mostFrequentWordsDictionary) {
    	T[] words = (T[]) dictionary.toArray();
        for (int i = 0; i < dictionary.getCurrentSize(); i++) {
			T word = words[i];
            int frequency = getFrequencyOf(word);
            if (frequency < 5) {
                rareWordsDictionary.add(word);
            } else if (frequency >= 5 && frequency <= 8) {
                frequentWordsDictionary.add(word);
            } else {
                mostFrequentWordsDictionary.add(word);
            }
        }
    }
    
	private void ensureCapacity() {
        if (isFull()) {
            Object[] newItems = new Object[words.length * 2];
            System.arraycopy(words, 0, newItems, 0, words.length);
            words = newItems;
        }
    }
}
