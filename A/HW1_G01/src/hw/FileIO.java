package hw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class FileIO {
    public static void readFile(Text<Word> text, Dictionary<Word> dictionary) {
        File file = new File("text.txt"); // Assuming the file name is text.txt, change as needed
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
            	String wordStr = scanner.next().replaceAll("[^a-zA-Z0-9]", ""); // Remove punctuation
                Word word = new Word(wordStr.toLowerCase()); // Convert to lower case
                text.add(word); // Add to text bag

                if (!dictionary.contains(word)) {
                    dictionary.add(word); // Add to dictionary if not already present
                }                
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
