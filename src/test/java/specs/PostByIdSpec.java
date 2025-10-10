package specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static specs.BaseSpecs.baseRequestSpec;
import static specs.BaseSpecs.baseResponseSpec;

public class PostByIdSpec {

    public static RequestSpecification postByIdRequestSpec = with()
            .spec(baseRequestSpec)
            .basePath("/posts/20");

    public static ResponseSpecification postByIdResponseSpec = baseResponseSpec;
}