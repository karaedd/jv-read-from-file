package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FileWork {
    public String[] readFromFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while (value != null) {
                String[] words = value.split(" ");
                for (String word : words) {
                    if (word.toLowerCase().startsWith("w")) {
                        stringBuilder.append(word.toLowerCase()
                                .replaceAll("\\p{Punct}", ""))
                                .append(',');
                    }
                }
                value = bufferedReader.readLine();
            }
            if (stringBuilder.isEmpty()) {
                return new String[0];
            }
            return Arrays.stream(stringBuilder.toString()
                            .split(","))
                    .sorted().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
