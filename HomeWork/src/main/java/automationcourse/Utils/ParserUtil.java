package automationcourse.Utils;

public class ParserUtil {
    public static String getToken(String url) {
        return url.split("access_token=")[1].split("&")[0];
    }
    public static String getPostID(String postResponse) {
        return postResponse.split("post_id\":")[1].split("}}")[0];
    }
}
