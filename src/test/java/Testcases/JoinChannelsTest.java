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

public class JoinChannelsTest extends TestBase {

    RequestSpecification httpRequest;
    Response response;
    String name_with_empty_value_join = "";
    String name_with_legal_special_charecter_join = "channel_973_-993";
    String name_with_more_than_given_length_join = "jdssssspppsppssy88sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
    String name_with_not_legal_special_charecters_join = "(()ABDKLLNJNK))!@#$";
    String name_with_punctuation_join = ")())(())";
    String AUTHORIZATION_BOT = "Bearer xoxb-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426";


    @Test(description = "This method cannot be called by a bot user.")
    void botuserCannotCallMethod() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", AUTHORIZATION_BOT);

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", "plivo_slack_123");
        requestParams.put("validate", "true");


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
    void AspectAuthentication() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc3837842");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", "plivo_1200");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.create");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_auth");

    }


    @Test(description = "Channel Is Unknown")
    void unknownChannel() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", "xyz_2");
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
    void channelNotFound() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", "ksjdfh");
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
    void contentTypeIsInvalid() {

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

        requestParams.put("name", "plivo_slack");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.join");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_post_type");
    }


    @Test(description = "Request did not include a Content-Type header. ")
    void missingContentType() {
//Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", "plivo_slack");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.join");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "channel_not_found");
    }


    @Test(description = "Request did not include a Content-Type header. ")
    void missingFormData() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/x-www-form-urlencoded");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", "plivo_slack");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.join");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_form_data");
    }


    @Test(description = "To Join Existing cahnnel")
    void joinExistingChannelsTestCase() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", channel_name);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.join");

        String responseBody = response.getBody().asString();
        //  System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), true);
        Assert.assertEquals(jsonpath.get("already_in_channel"), true);

    }

    @Test(description = "If channel not created the create channel")
    void createNewChannelIfNotExist() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", "plivo_slack");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.join");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), true);
        Assert.assertEquals(jsonpath.get("channel.priority"), 0);
    }

    @Test(description = "If channel is Archived can't join channel")
    void archivedChannels() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", isArchivedChannelName);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.join");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "is_archived");


    }

    @Test(description = "Value passed for name contained unallowed special characters or upper case characters.")
    void invalidNameSpecial() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", ":::");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.join");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_name_specials");


    }

    @Test(description = "Value passed for name was empty.")
    void emptyNameJoin() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");
        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name_with_empty_value_join);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.join");

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_name_required");
    }

    @Test(description = "To validate Name Regex")
    void validateNameRegexJoin() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");
        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name_with_legal_special_charecter_join);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.create");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), true);
        Assert.assertEquals(jsonpath.get("channel.name"), name_with_legal_special_charecter_join);
    }

    @Test(description = "Value passed for name exceeded max length.")
    void validateLengthOfNameJoin() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");
        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name_with_more_than_given_length_join);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.create");

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_name_maxlength");
    }

    @Test(description = "Value passed for name contained unallowed special characters or upper case characters.")
    void nameContainsSpecialCharectersJoin() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");
        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name_with_not_legal_special_charecters_join);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.create");

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_name_specials");
    }

    @Test(description = "Value passed for name contained only punctuation.")
    void invalid_name_punctuation() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");
        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name_with_punctuation_join);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.create");

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_name_punctuation");
    }


}
