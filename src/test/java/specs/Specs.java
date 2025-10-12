package specs;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.filter.log.LogDetail.ALL;

public class Specs {

    public static RequestSpecification jsonPlaceholderRequestSpec = with()
            .baseUri(RestAssured.baseURI)
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all();

    public static ResponseSpecification responseSpec(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .log(ALL)
                .build();
    }
}
