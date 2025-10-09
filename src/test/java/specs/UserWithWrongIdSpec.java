package specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static specs.BaseSpecs.baseRequestSpec;
import static specs.BaseSpecs.baseResponseSpec;

public class UserWithWrongIdSpec {
    public static RequestSpecification getUserWithWrongIdRequestSpec= with()
            .spec(baseRequestSpec)
            .basePath("/users/24");

    public static ResponseSpecification getUserWithWrongIdResponseSpec = baseResponseSpec;
}
