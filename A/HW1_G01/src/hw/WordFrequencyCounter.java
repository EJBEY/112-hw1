package hw;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        // Create necessary dictionaries
        Dictionary<Word> dictionary = new Dictionary<>();
        RareWordsDictionary<Word> rareWordsDictionary = new RareWordsDictionary<>();
        FrequentWordsDictionary<Word> frequentWordsDictionary = new FrequentWordsDictionary<>();
        MostFrequentWordsDictionary<Word> mostFrequentWordsDictionary = new MostFrequentWordsDictionary<>();
        Text<Word> text = new Text<>();

        // Read file and populate dictionaries
        FileIO.readFile(text, dictionary);

        // Separate words into different dictionaries based on frequency
        text.separate(dictionary, rareWordsDictionary, frequentWordsDictionary, mostFrequentWordsDictionary);

        // Display results
        System.out.println("Text: " + text.getCurrentSize() + " words");
        text.displayItems();
        
        System.out.println("Rare Words Dictionary: " + rareWordsDictionary.getCurrentSize() + " words");
        rareWordsDictionary.displayItems();

        System.out.println("Frequent Words Dictionary: " + frequentWordsDictionary.getCurrentSize() + " words");
        frequentWordsDictionary.displayItems();

        System.out.println("Most Frequent Words Dictionary: " + mostFrequentWordsDictionary.getCurrentSize() + " words");
        mostFrequentWordsDictionary.displayItems();
    }
}
