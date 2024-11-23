import java.io.*;
import java.net.*;
import java.util.*;

public class Book {
    private static final String BOOK_URL = "https://www.gutenberg.org/cache/epub/1539/pg1539-images.html"; // URL for The Winter's Tale
    private static final String OUTPUT_FILE = "TheWintersTale_PigLatin.txt"; // Output file name

    public static void main(String[] args) {
        try {
            String bookText = readBookFromUrl(BOOK_URL);

            bookText = extractActualBookText(bookText);

            // Translate the content to Pig Latin
            String pigLatinText = translateToPigLatin(bookText);

            // Write the translated content to a file
            writeToFile(pigLatinText, OUTPUT_FILE);

            System.out.println("The book has been translated into Pig Latin and saved to: " + OUTPUT_FILE);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    // Reads the the book from a URL
    private static String readBookFromUrl(String urlString) throws IOException {
        StringBuilder content = new StringBuilder();
        URL url = new URL(urlString);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static String extractActualBookText(String text) {
        int start = text.indexOf("*** START OF THIS PROJECT GUTENBERG EBOOK");
        int end = text.indexOf("*** END OF THIS PROJECT GUTENBERG EBOOK");
        if (start == -1 || end == -1) {
            throw new IllegalStateException("Could not find the book content markers.");
        }

        return text.substring(start + 35, end).trim(); // +35 skips the marker part
    }

    // Translates the book text into Pig Latin
    private static String translateToPigLatin(String text) {
        StringBuilder translatedText = new StringBuilder();

        // Split text into words and translate each one
        String[] words = text.split("\\s+");
        for (String word : words) {
            translatedText.append(translateWord(word)).append(" ");
        }

        return translatedText.toString().trim();
    }

    // Translate an individual word using the PigLatinTranslator
    private static String translateWord(String word) {
        return PigLatinTranslator.translateWord(word); 
    }

    private static void writeToFile(String text, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(text);
        }
    }
}
