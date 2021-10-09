package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsFinder {
    public ArrayList<String> getWordsWithMaxConsonantsCount(String filePath) throws IOException {
        Map<String, Integer> dictionary = new HashMap<>();
        String text = this.getContentOfFile(filePath);

        Pattern pattern = Pattern.compile("(\\w+)");
        Matcher matcher = pattern.matcher(text);
        int maxCountOfConsonants = 0;

        while (matcher.find()) {
            String word = text.substring(matcher.start(), matcher.end());

            if (word.length() > 30) {
                word = word.substring(0, 30);
            }

            String normalizedWord = word.toUpperCase();

            if (dictionary.containsKey(normalizedWord)) continue;

            int consonantsCount = getMaxConsonantsChainCount(word);

            if (consonantsCount > maxCountOfConsonants) maxCountOfConsonants = consonantsCount;

            dictionary.put(normalizedWord, consonantsCount);
        }

        var words = dictionary.keySet().toArray();
        var countsOfConsonants = dictionary.values().toArray();

        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < dictionary.size(); i++) {
            if ((int) countsOfConsonants[i] == maxCountOfConsonants) {
                result.add(words[i].toString());
            }
        }

        return result;
    }

    public String getContentOfFile(String filePath) throws IOException {
        return Files.readString(Paths
                .get(filePath));
    }

    public int getMaxConsonantsChainCount(String word) {
        ArrayList<Character> consonants = new ArrayList<>(Arrays.asList('B', 'C', 'D', 'F', 'G', 'H',
                'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'));

        String normalizedWord = word.toUpperCase();
        int count = 0;
        int maxCount = 0;
        for (int i = 0; i < normalizedWord.length(); i++) {
            if (consonants.contains(normalizedWord.charAt(i))) {
                count++;
            } else {
                count = 0;
            }

            if(count > maxCount) maxCount = count;
        }

        return maxCount;
    }
}
