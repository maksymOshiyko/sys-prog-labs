package main;
import java.io.IOException;
import java.util.ArrayList;

public class Lab1 {
    public static void main(String[] args) throws IOException {
        String filePath = "src/resources/text.txt";
        WordsFinder wordsFinder = new WordsFinder();
        ArrayList<String> words = wordsFinder.getWordsWithMaxConsonantsCount(filePath);

        for (int i = 0; i < words.size(); i++) {
            System.out.println(words.get(i));
        }
    }
}
