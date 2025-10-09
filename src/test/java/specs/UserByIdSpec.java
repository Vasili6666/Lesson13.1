package specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static specs.BaseSpecs.baseRequestSpec;
import static specs.BaseSpecs.baseResponseSpec;

public class UserByIdSpec {
    public static RequestSpecification getUserByIdRequestSpec = with()
            .spec(baseRequestSpec)
            .basePath("/users/4");

    public static ResponseSpecification getUserByIdResponseSpec = baseResponseSpec;

}
