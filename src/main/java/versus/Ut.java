package versus;

import java.util.List;

public class Ut {
    public static final String DEFAULT_PREFIX = "vs ";

    /**
     * Returns the specified command, without its prefix and without whitespace at the start and end.
     * @param prefixes
     * @param command
     * @param trim
     * @return
     * @throws IllegalArgumentException
     */
    public static String pruneCommand(List<String> prefixes, String command, boolean trim) throws IllegalArgumentException {
        for (String prefix : prefixes) {
            if (command.startsWith(prefix)) {
                int start = prefix.length();
                if (start == (trim ? command.trim().length() : command.length())) {
                    return "";
                } else if (trim) {
                    return command.substring(start).trim();
                } else {
                    return command.substring(start);
                }
            }
        }

        throw new IllegalArgumentException("Command did not start with one of the given prefixes");
    }
}
