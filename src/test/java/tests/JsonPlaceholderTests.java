package tests;

import models.lombok.BodyLombokModelsUpdatePostTest;
import models.lombok.ResponceLomboktUpdatePostTest;
import models.lombok.ResponseLombokPostByIdTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static specs.Specs.jsonPlaceholderRequestSpec;
import static specs.Specs.responseSpec;

public class JsonPlaceholderTests extends TestBase{

    @Test
    @DisplayName("Изменение поста с Lombok (PUT /posts/1)")
    public void lombokUpdatePostTest() {
        BodyLombokModelsUpdatePostTest data = new BodyLombokModelsUpdatePostTest(1,
                "Basil post updated",
                "Fata viam invenient. (пер. «Судьба найдёт путь.»)",
                1);

        ResponceLomboktUpdatePostTest response = step("Выполняем PUT-запрос", () ->
                given(jsonPlaceholderRequestSpec)
                        .body(data)
                        .when()
                        .put("/posts/1")
                        .then()
                        .spec(responseSpec(200))
                        .extract()
                        .as(ResponceLomboktUpdatePostTest.class)
        );

        step("Проверяем ответ", () -> {
            assertEquals(data.getTitle(), response.getTitle(), "Заголовок поста не совпадает!");
            assertEquals(data.getUserId(), response.getUserId(), "userId должен быть равен 1");
            assertEquals(data.getBody(), response.getBody(), "Body не совпадает");
        });
    }

    @Test
    @DisplayName("GET поста по ID")
    public void getPostByIdTest() {
        ResponseLombokPostByIdTest response = step("GET /posts/20", () ->
                given(jsonPlaceholderRequestSpec)
                        .when()
                        .get("/posts/20")
                        .then()
                        .spec(responseSpec(200))
                        .extract()
                        .as(ResponseLombokPostByIdTest.class)
        );

        step("Проверяем ответ", () -> {
            assertNotNull(response.getId(), "ID поста пустой!");
            assertNotNull(response.getTitle(), "Заголовок поста пустой!");
            assertNotNull(response.getBody(), "Body поста пустой!");
            assertNotNull(response.getUserId(), "userId поста пустой!");
        });
    }

    @Test
    @DisplayName("Создание нового поста (POST /posts)")
    public void createPostTest() {
        BodyLombokModelsUpdatePostTest data = new BodyLombokModelsUpdatePostTest();
        data.setTitle("Basil post");
        data.setBody("Fata viam invenient.");
        data.setUserId(1);

        ResponceLomboktUpdatePostTest response = step("POST /posts", () ->
                given(jsonPlaceholderRequestSpec)
                        .body(data)
                        .when()
                        .post("/posts")
                        .then()
                        .spec(responseSpec(201))
                        .extract()
                        .as(ResponceLomboktUpdatePostTest.class)
        );

        step("Проверяем ответ", () -> {
            assertEquals(data.getTitle(), response.getTitle());
            assertEquals(data.getBody(), response.getBody());
            assertEquals(data.getUserId(), response.getUserId());
        });
    }

    @Test
    @DisplayName("Удаление поста (DELETE /posts/1)")
    public void deletePostTest() {
        step("DELETE /posts/1", () ->
                given(jsonPlaceholderRequestSpec)
                        .when()
                        .delete("/posts/1")
                        .then()
                        .spec(responseSpec(200)) // jsonplaceholder возвращает 200 на DELETE
        );
    }

    @Test
    @DisplayName("Получаем всех пользователей (GET /users)")
    public void getAllUsersTest() {
        step("GET /users", () ->
                given(jsonPlaceholderRequestSpec)
                        .when()
                        .get("/users")
                        .then()
                        .spec(responseSpec(200))
                        .body("$", not(empty()))
                        .body("id", everyItem(greaterThan(0)))
        );
    }

    @Test
    @DisplayName("Получаем пользователя по ID (GET /users/4)")
    public void getUserByIdTest() {
        step("GET /users/4", () ->
                given(jsonPlaceholderRequestSpec)
                        .when()
                        .get("/users/4")
                        .then()
                        .spec(responseSpec(200))
                        .body("id", equalTo(4))
                        .body("$", not(empty()))
        );
    }

    @Test
    @DisplayName("Получаем пользователя по неверному ID (GET /users/24)")
    public void getUserWithWrongIdTest() {
        step("GET /users/24", () ->
                given(jsonPlaceholderRequestSpec)
                        .when()
                        .get("/users/24")
                        .then()
                        .spec(responseSpec(404))
        );
    }
}
