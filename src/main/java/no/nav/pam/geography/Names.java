package no.nav.pam.geography;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Internal utility class for location names
class Names {

    // Location name word regexp matching delimiter prefix and the word
    private static final Pattern LOC_WORD_WITH_DELIMITER = Pattern.compile("([ (-]*)([^ (-]+)");
    // Regexp matching location name words that shall never be capitalized
    private static final Pattern NO_CAP_WORDS = Pattern.compile("og|i");

    private static final Pattern WHITESPACE = Pattern.compile("^\\s+$");

    static String capitalizeLocationName(String locationName) {
        if (locationName == null || locationName.isEmpty() || WHITESPACE.matcher(locationName).matches()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        Matcher matcher = LOC_WORD_WITH_DELIMITER.matcher(locationName.toLowerCase());
        while (matcher.find()) {
            String delimiter = matcher.group(1);
            String word = matcher.group(2);
            result.append(delimiter).append(NO_CAP_WORDS.matcher(word).matches() ? word : capitalize(word));
        }
        return result.toString();
    }

    // Uppercase first character of string
    private static String capitalize(String name) {
        if (name.isEmpty()) {
            return name;
        }
        char[] chars = name.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

}
