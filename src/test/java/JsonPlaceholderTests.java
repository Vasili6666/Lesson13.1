
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class JsonPlaceholderTests {

    public static RequestSpecification requestSpec;

    @BeforeAll
    public static void setUp() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }

    @Test
    @DisplayName("Получаем всех пользователей")
    public void getAllUsersTest() {
        given()
                .spec(requestSpec)
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
        given()
                .spec(requestSpec)
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
        given()
                .spec(requestSpec)
                .when()
                .get("/users/24")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    @DisplayName("GET поста по ID")
    public void getPostByIdTest() {
        given()
                .spec(requestSpec)
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
        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
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
        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
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
        given()
                .spec(requestSpec)
                .when()
                .delete("/posts/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }
}
