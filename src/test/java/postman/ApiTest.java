package postman;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {
    private static String URL = "https://postman-echo.com";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = URL;
    }

    @Test
    public void testGetRequest() {

        given().basePath("/get")
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get()
                .then().log().all()
                .statusCode(200)
                .body("headers", not(emptyArray()))
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));

    }

    @Test
    public void deleteRequestTest() {

        given()
                .basePath("/delete")
                .contentType(ContentType.TEXT)
                .body("This is expected to be sent back as part of response body.")
                .when()
                .delete()
                .then().log().all()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."));
    }

    @Test
    public void postFormDataRequestTest() {

        given()
                .basePath("/post")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post()
                .then().log().body()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }

    @Test
    public void postRawTextRequestTest() {

        String json = "{\"text\": \"value\"}";

        given()
                .basePath("/post")
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post()
                .then().log().all()
                .statusCode(200)
                .body("data.text", equalTo("value"));
    }

    @Test
    public void putRequestTest() {
        given()
                .basePath("/put")
                .contentType(ContentType.TEXT)
                .body("This is expected to be sent back as part of response body.")
                .when()
                .put()
                .then().log().all()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."));
    }

    @Test
    public void patchRequestTest() {
        given()
                .basePath("/put")
                .contentType(ContentType.TEXT)
                .body("This is expected to be sent back as part of response body.")
                .when()
                .put()
                .then().log().all()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."));
    }
}
