package automationcourse.HW_Lesson_13;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static automationcourse.Utils.ParserUtil.getPostID;
import static automationcourse.Utils.ParserUtil.getToken;
import static automationcourse.Utils.RequestUtil.*;
import static org.testng.Assert.assertTrue;

public class VK_Test {
    private static final Logger log = Logger.getLogger(VK_Test.class);
    public final static String AUTH_REQUEST = "https://oauth.vk.com/authorize?client_id=7045605&display=page&redirect_uri=https://oauth.vk.com/blank.html&callback&scope=wall&response_type=token&v=5.101&state=123456";
    private final static String AUTH_RESPONSE = "https://oauth.vk.com/blank.html#access_token=9c6830b6daeac5329c741d257f57502abe83b89a9c8f6ef8f01b45a27a357fe19f4e7047649216ae06a92&expires_in=86400&user_id=7387438&state=123456";
    private String token;
    private String postID;

    @Test
    public void postMessageOnTheWallTest() {
        token = getToken(AUTH_RESPONSE);
        log.warn("For current test URL was hardcoded");
        String postResponse = apiPostWallMessage("Message", token);
        postID = getPostID(postResponse);
        String wallPost = apiGetByIDWallMessage(postID, token);
        assertTrue(wallPost.contains("\"id\":"+ postID));
        log.info("Message posted");
    }

    @Test(dependsOnMethods = "postMessageOnTheWallTest")
    public void editMessageOnTheWallTest() {
        String editedWallResponse = apiEditWallMessage(postID, "Edited message", token);
        assertTrue(editedWallResponse.contains("\"post_id\":"+ postID));
        log.info("Message edited");
    }

    @Test(dependsOnMethods = "editMessageOnTheWallTest")
    public void deleteMessageOnTheWallTest() {
        String deleteResponse = apiDeleteWallMessage(postID, token);
        assertTrue(deleteResponse.contains("\"response\":1"));
        log.info("Message deleted");
    }
}
