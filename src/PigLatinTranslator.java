import java.io.*;
import java.net.*;
import java.util.*;

public class Book {
    private List<String> text = new ArrayList<>();
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public void readFromHTML(String url) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            boolean isMainContent = false;
            StringBuilder content = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("*** START OF THIS PROJECT GUTENBERG EBOOK")) {
                    isMainContent = true;
                    continue;
                } else if (line.contains("*** END OF THIS PROJECT GUTENBERG EBOOK")) {
                    break;
                }

                if (isMainContent) {
                    content.append(line).append("\n");
                }
            }

            String rawText = content.toString().replaceAll("<[^>]+>", "").replaceAll("&nbsp;", " ").trim();
            String[] lines = rawText.split("\n");

            for (String textLine : lines) {
                if (!textLine.isBlank()) {
                    text.add(textLine.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void translateToPigLatin() {
        List<String> translatedText = new ArrayList<>();

        for (String line : text) {
            String[] words = line.split("\\s+");
            StringBuilder translatedLine = new StringBuilder();

            for (String word : words) {
                translatedLine.append(toPigLatin(word)).append(" ");
            }
            translatedText.add(translatedLine.toString().trim());
        }

        text = translatedText;
    }

    private String toPigLatin(String word) {
        String vowels = "AEIOUaeiou";
        int firstVowelIndex = -1;

        // Find the first vowel
        for (int i = 0; i < word.length(); i++) {
            if (vowels.indexOf(word.charAt(i)) != -1) {
                firstVowelIndex = i;
                break;
            }
        }

        if (firstVowelIndex == -1) {
            return word + "ay"; // No vowels
        } else if (firstVowelIndex == 0) {
            return word + "way"; // Starts with a vowel
        } else {
            return word.substring(firstVowelIndex) + word.substring(0, firstVowelIndex) + "ay";
        }
    }

    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(title + ".txt", true))) {
            for (String line : text) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Book book = new Book("TheWinter'sTale_PigLatin");
        book.readFromHTML("https://www.gutenberg.org/cache/epub/1539/pg1539-images.html");
        book.translateToPigLatin();
        book.writeToFile();

        System.out.println("Translation complete! Check the file: TheWinter'sTale_PigLatin.txt");
    }
}

