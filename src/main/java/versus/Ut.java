package versus;

import java.util.List;

public class Ut {
    public static final String DEFAULT_PREFIX = "vs ";
    public static final String PFP_LINK = "https://cdn.discordapp.com/avatars/984971448080822313/01967ceb57e287e4e7e0b60c7131987f.webp?size=40";

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
