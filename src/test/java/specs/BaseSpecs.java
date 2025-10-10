package specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static helpers.CustomAllureListener.withCustomTemplates;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.filter.log.LogDetail.*;

public class BaseSpecs {

    public static RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri("https://jsonplaceholder.typicode.com")
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            //.addFilter(withCustomTemplates())
            //.addFilter(new AllureRestAssured())
            .log(URI)
            .log(BODY)
            .log(HEADERS)
            .build();

    public static ResponseSpecification baseResponseSpec = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .log(STATUS)
            .log(BODY)
            .build();
}
