import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCount {

    private Map<String, Integer> wordCounts;
    private int curWordCount;

    public static void main(String[] args) throws FileNotFoundException {
    	WordCount wc = new WordCount(args[0]);
    }

    public WordCount(String fileName) throws FileNotFoundException {
        wordCounts = new HashMap<>();
        Scanner fileScanner = new Scanner(new File(fileName));
        while (fileScanner.hasNext()) {
            String curWord = fileScanner.next();
            if (wordCounts.get(curWord) == null) {
                wordCounts.put(curWord, 1);
            } else {
                curWordCount = wordCounts.get(curWord);
                wordCounts.put(curWord, curWordCount + 1);

            }
        }
        for (String name: wordCounts.keySet()) {
			System.out.println(name + wordCounts.get(name));
		}

    }
}