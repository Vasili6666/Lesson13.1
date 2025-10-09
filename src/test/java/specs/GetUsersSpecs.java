package specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static specs.BaseSpecs.baseRequestSpec;
import static specs.BaseSpecs.baseResponseSpec;

public class GetUsersSpecs {

    public static RequestSpecification getUsersRequestSpec = with()
            .spec(baseRequestSpec)
            .basePath("/users");

    public static ResponseSpecification getUsersResponseSpec = baseResponseSpec;
}