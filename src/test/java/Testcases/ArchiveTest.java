package Testcases;

import TestBase.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ArchiveTest extends TestBase {
    RequestSpecification httpRequest;
    Response response;
    String AUTHORIZATION_BOT = "Bearer xoxb-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426";


    @Test(description = "To validate the positive test case")
    void positiveTestCase() {


        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("channel", isArchivedChannel);

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());

        //Response object
        response = httpRequest.request(Method.POST, "/channels.archive");

        //print response in console window

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        String ERROR = response.jsonPath().get("error");
        if (ERROR.equals("already_archived")) {
            JsonPath jsonpath = response.jsonPath();
            Assert.assertEquals(jsonpath.get("ok"), false);
            Assert.assertEquals(jsonpath.get("error"), "already_archived");
        } else {

            JsonPath jsonpath = response.jsonPath();
            Assert.assertEquals(jsonpath.get("ok"), true);
        }

    }


    @Test(description = "Validate if the Channel is archived successfully")
    void archivedchannelValidation() {


        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("channel", "CP04JNNSH");

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());

        //Response object
        response = httpRequest.request(Method.POST, "/channels.archive");

        //print response in console window

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);


        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "already_archived");


    }


    @Test(description = "To validate the Invalid Channel")
    void negativeTestCase() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("channel", wrongChannelNumber);

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());

        //Response object
        response = httpRequest.request(Method.POST, "/channels.archive");

        //print response in console window

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "channel_not_found");

    }

    @Test(description = "This method cannot be called by a bot user.")
    void botuserCannotCallMethodArchive() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", AUTHORIZATION_BOT);

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("channel", isArchivedChannel);


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.join");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_auth");
    }


    @Test(description = "Some aspect of authentication cannot be validated")
    void AspectAuthenticationArchive() {


        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc3837842");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("channel", isArchivedChannel);

        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.archive");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_auth");

    }


    @Test(description = "Channel Is Unknown")
    void unknownChannelArchive() {


        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("channel", isArchivedChannel);

        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "unknown_method");

    }

    @Test(description = "Channel Not Found")
    void channelNotFoundArchive() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("channel", isArchivedChannel);


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.create");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "no_channel");

    }


    @Test(description = "The specified Content-Type was invalid. ")
    void contentTypeIsInvalidArchive() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //private final String AUTHORIZATION_1 = "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426";


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "text/xml");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("channel", isArchivedChannel);


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.archive");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_post_type");
    }


    @Test(description = "Request did not include a Content-Type header. ")
    void missingContentTypeArchive() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("channel", isArchivedChannel);


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.archive");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "channel_not_found");
    }


    @Test(description = "Request did not include a Content-Type header. ")
    void missingFormDataArchive() {


        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/x-www-form-urlencoded");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("channel", isArchivedChannel);


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.archive");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_form_data");
    }


}
	
	



