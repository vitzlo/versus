package versus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileUt {
    public static String csvValueAtKeyCell(File csv, String keyColumn, String keyCell, String valueColumn)
            throws IOException, IllegalArgumentException {
        int keyIndex, valueIndex;

        try (BufferedReader reader = new BufferedReader(new FileReader(csv))) {
            String line = reader.readLine();

            List<String> headers = Arrays.stream(line.split(",")).toList();
            if (!headers.contains(keyColumn)) {
                throw new IllegalArgumentException("Key column not found in the headers");
            } else {
                keyIndex = headers.indexOf(keyColumn);
            }
            if (!headers.contains(valueColumn)) {
                throw new IllegalArgumentException("Value column not found in the headers");
            } else {
                valueIndex = headers.indexOf(valueColumn);
            }

            String[] splits;
            line = reader.readLine();
            while (line != null) {
                splits = line.split(",");
                if (splits[keyIndex].equals(keyCell)) {
                    reader.close();
                    return splits[valueIndex];
                }

                line = reader.readLine();
            }

            reader.close();
            throw new IllegalArgumentException("Key value not found in the specified key column");
        }
    }
}
