package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static specs.BaseSpecs.baseRequestSpec;
import static specs.BaseSpecs.baseResponseSpec;

public class UpdatePostSpecs {

    public static RequestSpecification updatePostRequestSpec = with()
            .spec(baseRequestSpec)
            .basePath("/posts/1");

    public static ResponseSpecification updatePostResponseSpec = baseResponseSpec;
}

