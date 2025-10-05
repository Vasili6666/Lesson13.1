import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonPlaceholderTests {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Test
    @DisplayName("Получаем всех пользователей")
    public void getAllUsersTest() {
        RestAssured.baseURI = BASE_URL;

        given()
                .when()
                .get("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Test
    @DisplayName("GET post by ID")
    public void getPostByIdTest() {
        RestAssured.baseURI = BASE_URL;

        given()
                .when()
                .get("/posts/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(1));
    }

    @Test
    @DisplayName("POST create new post")
    public void createPostTest() {
        RestAssured.baseURI = BASE_URL;

        given()
                .contentType(ContentType.JSON)
                .body("{\"title\": \"New post\", \"body\": \"Post body\", \"userId\": 1}")
                .when()
                .post("/posts")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", is("New post"));
    }

    @Test
    @DisplayName("PUT update post")
    public void updatePostTest() {
        RestAssured.baseURI = BASE_URL;

        given()
                .contentType(ContentType.JSON)
                .body("{\"id\": 1, \"title\": \"Updated post\", \"body\": \"Updated body\", \"userId\": 1}")
                .when()
                .put("/posts/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("title", is("Updated post"));
    }

    @Test
    @DisplayName("DELETE post")
    public void deletePostTest() {
        RestAssured.baseURI = BASE_URL;

        given()
                .when()
                .delete("/posts/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200); // JSONPlaceholder возвращает 200 даже при удалении
    }
}
