package automationcourse.Utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class RequestUtil {

    public static String  apiPostWallMessage(String message, String token) {
        URIBuilder builder = createBuilder("post", token,"message", message, null, null);
        return apiRequest(builder);
    }

    public static String apiGetByIDWallMessage(String postID, String token) {
        URIBuilder builder = createBuilder("getById", token,"posts", "7387438_" + postID, null, null);
        return apiRequest(builder);
    }

    public static String apiEditWallMessage(String postID, String message, String token) {
        URIBuilder builder = createBuilder("edit", token,"post_id", postID, "message", message);
        return apiRequest(builder);
    }

    public static String apiDeleteWallMessage(String postID, String token) {
        URIBuilder builder = createBuilder("delete", token, "post_id", postID, null, null);
        return apiRequest(builder);
    }

    private static String apiRequest(URIBuilder builder) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = null;
        try {
            request = new HttpGet(builder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String requestResponse = null;
        try {
            requestResponse = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestResponse;
    }

    private static URIBuilder createBuilder(String methodType, String token, String param1, String value1, String param2, String value2) {
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(String.format("https://api.vk.com/method/wall.%s?", methodType));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        builder.setParameter("access_token", token)
                .setParameter("owner_id", "7387438")
                .setParameter(param1, value1)
                .setParameter("v", "5.101");
        if(param2 != null && value2 != null) {
            builder.setParameter(param2, value2);
        }
        return builder;
    }

}
