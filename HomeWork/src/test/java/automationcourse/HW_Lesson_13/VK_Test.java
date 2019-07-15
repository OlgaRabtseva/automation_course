package automationcourse.HW_Lesson_13;

import org.testng.annotations.Test;

import static automationcourse.Utils.ParserUtil.getPostID;
import static automationcourse.Utils.ParserUtil.getToken;
import static automationcourse.Utils.RequestUtil.*;
import static org.testng.Assert.assertTrue;

public class VK_Test {
    public final static String AUTH_REQUEST = "https://oauth.vk.com/authorize?client_id=7045605&display=page&redirect_uri=https://oauth.vk.com/blank.html&callback&scope=wall&response_type=token&v=5.101&state=123456";
    public final static String AUTH_RESPONSE = "https://oauth.vk.com/blank.html#access_token=57e9465ec1cc7627177db603d0bf8ece4cb39bb66453b685e70193b2af707aa4def6af03dc08d1637ca0c&expires_in=86400&user_id=7387438&state=123456";
    private String token;
    private String postID;

    @Test
    public void postMessageOnTheWallTest() {
        token = getToken(AUTH_RESPONSE);
        String postResponse = apiPostWallMessage("Message", token);
        postID = getPostID(postResponse);
        String wallPost = apiGetByIDWallMessage(postID, token);
        assertTrue(wallPost.contains("\"id\":"+ postID));
    }

    @Test(dependsOnMethods = "postMessageOnTheWallTest")
    public void editMessageOnTheWallTest() {
        String editedWallResponse = apiEditWallMessage(postID, "Edited message", token);
        assertTrue(editedWallResponse.contains("\"post_id\":"+ postID));
    }

    @Test(dependsOnMethods = "editMessageOnTheWallTest")
    public void deleteMessageOnTheWallTest() {
        String deleteResponse = apiDeleteWallMessage(postID, token);
        assertTrue(deleteResponse.contains("\"response\":1"));
    }
}
