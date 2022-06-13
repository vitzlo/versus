package versus;

import discord4j.core.object.entity.Message;
import versus.data.DataAccess;

import java.util.List;

public class Ut {
    public static final String DEFAULT_PREFIX = "vs ";
    public static final String PLACEHOLDER_PREFIX = "<prefix>";
    public static final String PFP_LINK = "https://cdn.discordapp.com/avatars/984971448080822313/01967ceb57e287e4e7e0b60c7131987f.webp?size=40";

    public static final DataAccess dataAccess = new DataAccess();

    /**
     * Returns the specified command, without its prefix and without whitespace at the start and end.
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

    public static String fieldValuePairs(Object... items) {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < items.length / 2; i++) {
            value.append(String.format("**%s**: %s", items[2 * i].toString(), items[2 * i + 1].toString()));
            if (i < items.length / 2 - 1) {
                value.append('\n');
            }
        }

        return value.toString();
    }

    public static String getMessageGuildPrefix(Message message) {
        return message.getGuildId().isPresent()
                ? dataAccess.getGuildPrefixElseDefault(message.getGuildId().get().asString())
                : Ut.DEFAULT_PREFIX;
    }
}
