package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.lombok.BodyLombokModelsUpdatePostTest;
import models.lombok.ResponceLomboktUpdatePostTest;
import models.pojo.BodyModelsUpdatePostTest;
import models.pojo.ResponceUpdatePostTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @DisplayName("Изменение поста с Lombok")
    public void lombokUpdatePostTest() {

        BodyLombokModelsUpdatePostTest data = new BodyLombokModelsUpdatePostTest();
        data.setId("1");
        data.setTitle("Basil post updated");
        data.setBody("Fata viam invenient.(пер. «Судьба найдёт путь.»)");
        data.setUserId("1");

        // используем только Lombok-класс для ответа
        ResponceLomboktUpdatePostTest response = given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .put("/posts/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract()
                .as(ResponceLomboktUpdatePostTest.class); // <--- убран каст

        // проверки
        assertEquals(data.getTitle(), response.getTitle(), "Заголовок поста не совпадает!");
        assertEquals(data.getUserId(), response.getUserId(), "userId должен быть равен 1");
        assertEquals(data.getBody(), response.getBody(), "Body не совпадает");
    }



    /*@Test
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
    }*/

    /*@Test
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
*/
    /*@Test
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
*/
    /*@Test
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
    }*/

    /*@Test
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
*/
   /* @Test
    @DisplayName("Изменение поста")
    public void pojoUpdatePostTest() {

        BodyModelsUpdatePostTest data = new BodyModelsUpdatePostTest();
        data.setId("1");
        data.setTitle("Basil post updated");
        data.setBody("Fata viam invenient.(пер. «Судьба найдёт путь.»)");
        data.setUserId("1");

        ResponceUpdatePostTest response = given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .put("/posts/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract()
                .as(ResponceUpdatePostTest.class);

        assertEquals("Basil post updated", response.getTitle(), "Заголовок поста не совпадает!");
        assertEquals("1", response.getUserId(), "userId должен быть равен 1");
    }

   */
    /*@Test
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
    }*/
}
