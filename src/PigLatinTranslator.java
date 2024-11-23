public class PigLatinTranslator {
    public static String translateWord(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String word = input;
        String punctuation = "";
        if (!Character.isLetter(word.charAt(word.length() - 1))) {
            punctuation = word.substring(word.length() - 1);
            word = word.substring(0, word.length() - 1);
        }
        boolean isCapitalized = Character.isUpperCase(word.charAt(0));
        word = word.toLowerCase();

        // Vowel check
        if ("aeiou".indexOf(word.charAt(0)) != -1) {
            word = word + "yay";
        } else {
            // Consonant handling
            int vowelIndex = -1;
            for (int i = 0; i < word.length(); i++) {
                if ("aeiou".indexOf(word.charAt(i)) != -1) {
                    vowelIndex = i;
                    break;
                }
            }
            if (vowelIndex != -1) {
                word = word.substring(vowelIndex) + word.substring(0, vowelIndex) + "ay";
            } else {
                word = word + "ay"; // No vowels in the word
            }
        }
        if (isCapitalized) {
            word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
        }
        return word + punctuation;
    }

    public static String translate(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String[] words = input.split(" "); // Split by spaces
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(translateWord(word)).append(" "); // Translate each word
        }
        return result.toString().trim(); // Remove trailing space
    }

    public static Book translate(Book input) {
        Book output = new Book();
        output.setTitle(input.getTitle() + " (Translated to Pig Latin)");

        for (String line : input.getText()) {
            output.addLine(translate(line)); // Translate each line in the book
        }
        return output;
    }
}

