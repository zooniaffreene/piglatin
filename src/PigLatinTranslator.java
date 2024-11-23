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

        if ("aeiou".indexOf(word.charAt(0)) != -1) {
            word = word + "yay";
        } else {
            // Handle consonant-starting words
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
                word = word + "ay"; // For words with no vowels
            }
        }

        if (isCapitalized) {
            word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
        }

        return word + punctuation;
    }
}
