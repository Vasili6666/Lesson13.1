import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
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
    @DisplayName("Получаем пользователя по ID")
    public void getUserByIdTest() {
        RestAssured.baseURI = BASE_URL;

        given()
                .when()
                .get("/users/4")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Test
    @DisplayName("Получаем пользователя по неверному ID")
    public void getUserByWrongIdTest() {
        RestAssured.baseURI = BASE_URL;

        given()
                .when()
                .get("/users/24")
                .then()
                .log().status()
                .log().body()
                .statusCode(404)
                .body("$", not(empty()));
    }

    @Test
    @DisplayName("GET post by ID")
    public void getPostByIdTest() {
        RestAssured.baseURI = BASE_URL;

        given()
                .when()
                .get("/posts/20")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(20));
    }

    @Test
    @DisplayName("Создание нового поста")
    public void createPostTest() {
        RestAssured.baseURI = BASE_URL;

        given()
                .contentType(JSON)
                .body("{\"title\": \"Basil post\", \"body\": \"Fata viam invenient.\", \"userId\": 1}")
                .when()
                .post("/posts")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", is("Basil post"));
    }

    @Test
    @DisplayName("Изменение поста")
    public void updatePostTest() {
        RestAssured.baseURI = BASE_URL;

        given()
                .contentType(JSON)
                .body("{\"id\": 1, \"title\": \"Basil post updated\", \"body\": \"Fata viam invenient.(пер. «Судьба найдёт путь.»)\", \"userId\": 1}")
                .when()
                .put("/posts/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("title", is("Basil post updated"));
    }

    @Test
    @DisplayName("Удаление поста")
    public void deletePostTest() {
        RestAssured.baseURI = BASE_URL;

        given()
                .when()
                .delete("/posts/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }
}
