package versus;

public class ApiUt {
    public static final String IO_ERROR_MSG = "Connection could not be made to the server. Please try again later.";
    public static final String BAD_JSON_MSG = "Received a JSON which could not be parsed. Please try again later.";

    public static String encodeUrl(String url) {
        return url.replaceAll("#", "%23");
    }
}
