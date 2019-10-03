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

public class CreateChannelsTest extends TestBase {


    RequestSpecification httpRequest;
    Response response;

    String name_with_legal_special_charecter = "channl870996t86";
    String name_with_more_than_given_length = "jdssuisssssssy88ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
    String name_with_not_legal_special_charecters = "(()OIP))!@#$";
    String name_with_empty_value = "";
    String name_with_punctuation = ")())))";
    String AUTHORIZATION_BOT = "Bearer xoxb-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426";


    @Test(description = "To Create cahnnel")
    public Response createChannelsTest() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        RestAssured.baseURI = "https://slack.com/api";
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
        response = httpRequest.request(Method.POST, "/channels.create");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), true);
        Assert.assertEquals(jsonpath.get("channel.name"), channel_name);
        return response;

    }


    @Test(description = "A channel cannot be created with the given name.")
    void nameAlreadyExist() {


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
        response = httpRequest.request(Method.POST, "/channels.create");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "name_taken");

    }


    @Test(description = "To validate Name Regex")
    void validateNameRegex() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");
        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name_with_legal_special_charecter);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.create");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), true);
        Assert.assertEquals(jsonpath.get("channel.name"), name_with_legal_special_charecter);
    }

    @Test(description = "Value passed for name exceeded max length.")
    void validateLengthOfName() {


        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");
        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name_with_more_than_given_length);
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
    void nameContainsSpecialCharecters() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");
        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name_with_not_legal_special_charecters);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.create");

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_name_specials");
    }

    @Test(description = "Value passed for name was empty.")
    void emptyName() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();

        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");
        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name_with_empty_value);
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.create");

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_name_required");
    }


    @Test(description = "This method cannot be called by a bot user.")
    void botuserCannotCallMethodCreate() {

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
        response = httpRequest.request(Method.POST, "/channels.create");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_auth");
    }


    @Test(description = "Some aspect of authentication cannot be validated")
    void AspectAuthenticationCreate() {

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
    void unknownChannelCreate() {
        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "xyz_1");
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
    void channelNotFoundCreate() {

        //Specify base URI
        RestAssured.baseURI = "https://slack.com/api";

        //Request object
        httpRequest = RestAssured.given();


        //Adding Headers to the Request
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer xoxp-780163983796-780163984788-780649463040-1798f6475e7344693a7412dc38378426");

        //Request Paylaod sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "kjdfg");
        requestParams.put("validate", "true");


        // attach above data to the request
        httpRequest.body(requestParams.toJSONString());
        //Response object
        response = httpRequest.request(Method.POST, "/channels.archive");

        httpRequest.log();
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "channel_not_found");

    }


    @Test(description = "The specified Content-Type was invalid. ")
    void contentTypeIsInvalidCreate() {

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
        response = httpRequest.request(Method.POST, "/channels.create");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_post_type");
    }


    @Test(description = "Request did not include a Content-Type header. ")
    void missingContentTypeCreate() {

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
        response = httpRequest.request(Method.POST, "/channels.create");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "no_channel");
    }


    @Test(description = "Request did not include a Content-Type header. ")
    void missingFormDataCreate() {

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
        response = httpRequest.request(Method.POST, "/channels.create");

        String responseBody = response.getBody().asString();
        //System.out.println("Response Body is:" +responseBody);

        JsonPath jsonpath = response.jsonPath();
        Assert.assertEquals(jsonpath.get("ok"), false);
        Assert.assertEquals(jsonpath.get("error"), "invalid_form_data");
    }


}
