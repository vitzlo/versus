package versus;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// TODO: to data access?
public class FileUt {
    public static final String DB_ERROR_MSG = "Database could not be contacted. Please try again later.";

    public static boolean csvContainsValue(File csv, String keyColumn, String keyCell)
            throws IOException, IllegalArgumentException {
        List<String> lines = FileUtils.readLines(csv, "UTF-8");

        List<String> headers = Arrays.stream(lines.get(0).split(",")).toList();
        if (!headers.contains(keyColumn)) {
            throw new IllegalArgumentException("Key column header not found in the CSV");
        }
        int keyIndex = headers.indexOf(keyColumn);

        for (int i = 1; i < lines.size(); i++) {
            String[] splits = lines.get(i).split(",");
            if (splits[keyIndex].equals(keyCell)) {
                return true;
            }
        }

        return false;
    }

    public static String csvValueAtKeyCell(File csv, String keyColumn, String keyCell, String valueColumn)
            throws IOException, IllegalArgumentException {
        List<List<String>> cells = csvToNestedList(csv);

        List<String> headers = cells.get(0);
        if (!headers.contains(keyColumn)) {
            throw new IllegalArgumentException("Key column header not found in the CSV");
        }
        int keyIndex = headers.indexOf(keyColumn);

        if (!headers.contains(valueColumn)) {
            throw new IllegalArgumentException("Value column header not found in the CSV");
        }
        int valueIndex = headers.indexOf(valueColumn);

        for (int i = 1; i < cells.size(); i++) {
            if (cells.get(i).get(keyIndex).equals(keyCell)) {
                return cells.get(i).get(valueIndex);
            }
        }

        throw new IllegalArgumentException("Key value not found in the specified key column");
    }

    public static void csvWriteLine(File csv, Map<String, String> headersToNewCells)
            throws IOException, IllegalArgumentException {
        List<List<String>> cells = csvToNestedList(csv);
        Map<Integer, String> indicesToNewCells = new HashMap<>();

        List<String> headers = cells.get(0);
        for (String header : headersToNewCells.keySet()) {
            if (!headers.contains(header)) {
                throw new IllegalArgumentException("Header not found in CSV");
            }

            indicesToNewCells.put(headers.indexOf(header), headersToNewCells.get(header));
        }

        cells.add(new ArrayList<>());
        csvWriteToLine(csv, cells, cells.size() - 1, indicesToNewCells);
    }

    public static void csvOverwriteLine(File csv, String keyColumn, String keyCell, Map<String, String> headersToNewCells)
            throws IOException, IllegalArgumentException {
        List<List<String>> cells = csvToNestedList(csv);
        Map<Integer, String> indicesToNewCells = new HashMap<>();

        List<String> headers = cells.get(0);
        if (!headers.contains(keyColumn)) {
            throw new IllegalArgumentException("Key column header not found in the CSV");
        }
        int keyIndex = headers.indexOf(keyColumn);

        for (String header : headersToNewCells.keySet()) {
            if (!headers.contains(header)) {
                throw new IllegalArgumentException("Header not found in CSV");
            }

            indicesToNewCells.put(headers.indexOf(header), headersToNewCells.get(header));
        }

        for (int i = 1; i < cells.size(); i++) {
            if (cells.get(i).get(keyIndex).equals(keyCell)) {
                cells.get(i).clear();
                csvWriteToLine(csv, cells, i, indicesToNewCells);
            }
        }

        throw new IllegalArgumentException("Key value not found in the specified key column");
    }

    private static void csvWriteToLine(File csv, List<List<String>> cells, int row, Map<Integer, String> indicesToNewCells) throws IOException {
        List<String> newLine = cells.get(row);
        newLine.clear();
        for (int i = 0; i < cells.get(0).size(); i++) {
            newLine.add("");
        }

        for (Map.Entry<Integer, String> entry : indicesToNewCells.entrySet()) {
            newLine.set(entry.getKey(), entry.getValue());
        }

        nestedListToCsv(csv, cells);
    }

    private static List<List<String>> csvToNestedList(File csv) throws IOException {
        List<String> lines = FileUtils.readLines(csv, "UTF-8");

        return lines.stream().map(line -> Arrays.stream(line.split(",")).collect(Collectors.toList())).collect(Collectors.toList());
    }

    private static void nestedListToCsv(File csv, List<List<String>> cells) throws IOException { // TODO: delete, faster implementation
        List<String> lines = new ArrayList<>();

        for (List<String> line : cells) {
            StringBuilder newLine = new StringBuilder();

            for (int i = 0; i < line.size(); i++) {
                newLine.append(line.get(i));

                if (i < line.size() - 1) {
                    newLine.append(",");
                }
            }

            lines.add(newLine.toString());
        }

        FileUtils.writeLines(csv, lines);
    }
}
