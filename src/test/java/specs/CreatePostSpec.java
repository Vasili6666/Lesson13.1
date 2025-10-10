package specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class CreatePostSpec {

    public static final RequestSpecification createPostRequestSpec =
            with().spec(BaseSpecs.baseRequestSpec)
                    .basePath("/posts");

    public static final ResponseSpecification createPostResponseSpec =
            BaseSpecs.baseResponseSpec;
}
