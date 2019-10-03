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

import java.util.ArrayList;

public class RenameChannelsTest extends TestBase {

    public String RenameChannelsname = "gfhf";
    public String name_with_legal_special_charecter_1 = "channels-12_--";
    public String name_with_more_than_given_length_1 = "jdssssssssy88sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
    public String name_with_not_legal_special_charecters_1 = "(()!)(@#$";
    public String name_with_punctuation_1 = ")))))))";
    RequestSpecification httpRequest;
    Response response;
    String AUTHORIZATION_BOT = "Bearer xoxb-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426";


    @Test(description = "To Rename Channels Name")
    void renameChannelsName() {

        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        CreateChannelsTest createChannelsTest = new CreateChannelsTest();
        Response responseObject = createChannelsTest.createChannelsTest();
        String ChannelName = responseObject.jsonPath().get("channel.id");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CNTLYMXMX");
        requestParams.put("name", RenameChannelsname);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.rename");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), true);
        Assert.assertEquals(jsonpath.get("channel.name"), RenameChannelsname);
        //List all the perivious name
        ArrayList<String> names = jsonpath.get("channel.previous_names");

    }


    @Test(description = "Invalid Channels Name")
    void inValidChannelsName() {

        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CNZ4NG3J");
        requestParams.put("name", RenameChannelsname);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.rename");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "channel_not_found");

    }


    //--------------------------------

    @Test(description = "This method cannot be called by a bot user.")
    void botuserCannotCallMethodRename() {

        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", AUTHORIZATION_BOT);

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CNZ4NRGV6");
        requestParams.put("name", "plivo_slack_123");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.rename");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_auth");
    }


    @Test(description = "Some aspect of authentication cannot be validated")
    void AspectAuthenticationRename() {
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc3837842");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CNZ4NRGV6");
        requestParams.put("name", "plivo_1200");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.rename");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_auth");

    }


    @Test(description = "Channel Is Unknown")
    void unknownChannelRename() {
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CNZ4NRGV6");
        requestParams.put("name", "xyz");
        requestParams.put("validate", "true");


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
    void channelNotFoundRename() {
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CP04JNNSH");
        requestParams.put("name", "kjdshkj");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.archive");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "channel_not_found");

    }


    @Test(description = "The specified Content-Type was invalid. ")
    void contentTypeIsInvalidRename() {

        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //private final String AUTHORIZATION_1 = "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426";


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "text/xml");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CNZ4NRGV6");
        requestParams.put("name", "plivo_slack");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.rename");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_post_type");
    }


    @Test(description = "Request did not include a Content-Type header. ")
    void missingContentTypeRename() {
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();

        //Adding Headers to the Request
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CNZ4NRGV6");
        requestParams.put("name", "plivo_slack");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.rename");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "channel_not_found");
    }


    @Test(description = "Request did not include a Content-Type header. ")
    void missingFormDataRename() {
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/x-www-form-urlencoded");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CNZ4NRGV6");
        requestParams.put("name", "plivo_slack");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.rename");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_form_data");
    }


    //-------------------------------------------


    @Test(description = "To validate Name Regex")
    void validateNameRegex_1() {
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");


        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CNZ4NRGV6");
        requestParams.put("name", name_with_legal_special_charecter_1);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.rename");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), true);
        Assert.assertEquals(jsonpath.get("channel.name"), name_with_legal_special_charecter_1);
    }

    @Test(description = "Value passed for name exceeded max length.")
    void validateLengthOfName_1() {

        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");


        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CNZ4NRGV6");
        requestParams.put("name", name_with_more_than_given_length_1);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.rename");

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_name_maxlength");
    }

    @Test(description = "Value passed for name contained unallowed special characters or upper case characters.")
    void nameContainsSpecialCharecters_1() {

        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");


        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CNZ4NRGV6");

        requestParams.put("name", name_with_not_legal_special_charecters_1);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.rename");

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_name_specials");
    }

    @Test(description = "Value passed for name was empty.")
    void emptyName_1() {

        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");


        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();


        requestParams.put("name", "");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.rename");

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_name_required");
    }

    @Test(description = "Value passed for name contained only punctuation.")
    void invalid_name_punctuation_1() {


        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();
        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");


        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("channel", "CNZ4NRGV6");
        requestParams.put("name", name_with_punctuation_1);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.rename");

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_name_punctuation");
    }


}
