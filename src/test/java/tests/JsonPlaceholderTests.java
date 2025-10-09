package tests;

import models.lombok.BodyLombokModelsUpdatePostTest;
import models.lombok.ResponceLomboktUpdatePostTest;
import models.lombok.ResponseLombokPostByIdTest;
//import models.lombok.ResponseModelPostByIdTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.GetUsersSpecs.getUsersRequestSpec;
import static specs.GetUsersSpecs.getUsersResponseSpec;
import static specs.PostByIdSpec.postByIdRequestSpec;
import static specs.PostByIdSpec.postByIdResponseSpec;
import static specs.UpdatePostSpecs.updatePostRequestSpec;
import static specs.UpdatePostSpecs.updatePostResponseSpec;
import static specs.UserByIdSpec.getUserByIdRequestSpec;
import static specs.UserByIdSpec.getUserByIdResponseSpec;
import static specs.UserWithWrongIdSpec.getUserWithWrongIdRequestSpec;
import static specs.UserWithWrongIdSpec.getUserWithWrongIdResponseSpec;
import static specs.CreatePostSpec.createPostRequestSpec;
import static specs.CreatePostSpec.createPostResponseSpec;

public class JsonPlaceholderTests {

    @Test
    @DisplayName("Изменение поста с Lombok (PUT /posts/1)")
    public void lombokUpdatePostTest() {
        BodyLombokModelsUpdatePostTest data = new BodyLombokModelsUpdatePostTest();
        data.setId(1);
        data.setTitle("Basil post updated");
        data.setBody("Fata viam invenient. (пер. «Судьба найдёт путь.»)");
        data.setUserId(1);

        ResponceLomboktUpdatePostTest response = step("Выполняем PUT-запрос", () ->
                given(updatePostRequestSpec)
                        .body(data)
                        .when()
                        .put()
                        .then()
                        .spec(updatePostResponseSpec)
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
                given(postByIdRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(postByIdResponseSpec)
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
                given(createPostRequestSpec)
                        .body(data)
                        .when()
                        .post()
                        .then()
                        .spec(createPostResponseSpec)
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
                given(updatePostRequestSpec)
                        .when()
                        .delete()
                        .then()
                        .spec(updatePostResponseSpec)
        );
    }

    @Test
    @DisplayName("Получаем всех пользователей (GET /users)")
    public void getAllUsersTest() {
        step("GET /users", () ->
                given(getUsersRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(getUsersResponseSpec)
                        .body("$", not(empty()))
                        .body("id", everyItem(greaterThan(0))) // проверка, что у каждого пользователя id > 0
        );
    }

    @Test
    @DisplayName("Получаем пользователя по ID (GET /users/4)")
    public void getUserByIdTest() {
        step("GET /users/4", () ->
                given(getUserByIdRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(getUserByIdResponseSpec)
                        .body("id", equalTo(4)) // проверка, что id == 4
                        .body("$", not(empty()))
        );
    }

    @Test
    @DisplayName("Получаем пользователя по неверному ID (GET /users/24)")
    public void getUserWithWrongIdTest() {
        step("GET /users/24", () ->
                given(getUserWithWrongIdRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(getUserWithWrongIdResponseSpec)
                        .statusCode(404) // проверка, что возвращается 404
        );
    }
}











/*
package tests;

import models.lombok.BodyLombokModelsUpdatePostTest;
import models.lombok.ResponceLomboktUpdatePostTest;
import models.lombok.ResponseModelPostByIdTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.GetUsersSpecs.getUsersRequestSpec;
import static specs.GetUsersSpecs.getUsersResponseSpec;
import static specs.PostByIdSpec.postByIdRequestSpec;
import static specs.PostByIdSpec.postByIdResponseSpec;
import static specs.UpdatePostSpecs.updatePostRequestSpec;
import static specs.UpdatePostSpecs.updatePostResponseSpec;
import static specs.UserByIdSpec.getUserByIdRequestSpec;
import static specs.UserByIdSpec.getUserByIdResponseSpec;
import static specs.UserWithWrongIdSpec.getUserWithWrongIdRequestSpec;
import static specs.UserWithWrongIdSpec.getUserWithWrongIdResponseSpec;

public class JsonPlaceholderTests {

    @Test
    @DisplayName("Изменение поста с Lombok")
    public void lombokUpdatePostTest() {

        BodyLombokModelsUpdatePostTest data = new BodyLombokModelsUpdatePostTest();
        data.setId(1);
        data.setTitle("Basil post updated");
        data.setBody("Fata viam invenient. (пер. «Судьба найдёт путь.»)");
        data.setUserId(1);

        ResponceLomboktUpdatePostTest response = step("Выполняем PUT-запрос", () ->
        given(updatePostRequestSpec)
                .body(data)
                .when()
                .put()
                .then()
                .spec(updatePostResponseSpec)
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
    @DisplayName("Получаем всех пользователей")
    public void getAllUsersTest() {
        step("GET /users", () ->
        given(getUsersRequestSpec)
                .when()
                .get()
                .then()
                .spec(getUsersResponseSpec)
                .body("$", not(empty()))
        );
    }

    @Test
    @DisplayName("Получаем пользователя по ID")
    public void getUserByIdTest() {
        step("GET /user 4", () ->

        given(getUserByIdRequestSpec)
                .when()
                .get()
                .then()
                .spec(getUserByIdResponseSpec)
                .body("$", not(empty()))
        );
    }

    @Test
    @DisplayName("Получаем пользователя по неверному ID")
    public void getUserWithWrongIdTest() {
        given(getUserWithWrongIdRequestSpec)
                .when()
                .get()
                .then()
                .spec(getUserWithWrongIdResponseSpec);
    }


    @Test
    @DisplayName("GET поста по ID")
    public void getPostByIdTest() {
        BodyLombokModelsUpdatePostTest data = new BodyLombokModelsUpdatePostTest();
        data.setId(1);

        given(postByIdRequestSpec)
                .when()
                .then()
                .spec(postByIdResponseSpec)
                .expect().as(ResponseModelPostByIdTest.class);
                //.body("id", is(20));


    }

    */
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
*//*


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
    }*//*

}
*/
