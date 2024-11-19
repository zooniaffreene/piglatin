private static String translateWord(String input) {
    if (input == null || input.isEmpty()) {
        return input;
    }

    String word = input;
    String punctuation = "";

    // Check for punctuation at the end
    if (!Character.isLetter(word.charAt(word.length() - 1))) {
        punctuation = word.substring(word.length() - 1);
        word = word.substring(0, word.length() - 1);
    }

    // Check for capitalization
    boolean isCapitalized = Character.isUpperCase(word.charAt(0));

    // Lowercase the word for processing
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

    // Restore capitalization
    if (isCapitalized) {
        word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }

    // Add punctuation back
    return word + punctuation;
}


  // Add additonal private methods here.
  // For example, I had one like this:
  // private static String capitalizeFirstLetter(String input)

}
