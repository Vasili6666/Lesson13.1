package specs;

import static io.restassured.RestAssured.with;

public class CreatePostSpec {

    public static final io.restassured.specification.RequestSpecification createPostRequestSpec =
            with().spec(BaseSpecs.baseRequestSpec)
                    .basePath("/posts");

    public static final io.restassured.specification.ResponseSpecification createPostResponseSpec =
            BaseSpecs.baseResponseSpec;
}
/*package specs;

import static io.restassured.RestAssured.with;

public class CreatePostSpec {

    public static final io.restassured.specification.RequestSpecification createPostRequestSpec =
            with().spec(BaseSpecs.baseRequestSpec)
                    .basePath("/posts");

    public static final io.restassured.specification.ResponseSpecification createPostResponseSpec =
            BaseSpecs.baseResponseSpec;
}*/
