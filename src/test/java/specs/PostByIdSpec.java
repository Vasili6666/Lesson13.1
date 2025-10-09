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
/*
package specs;

import static io.restassured.RestAssured.with;

public class PostByIdSpec {

    public static final io.restassured.specification.RequestSpecification postByIdRequestSpec =
            with().spec(BaseSpecs.baseRequestSpec)
                    .basePath("/posts/20"); // id поста можно менять

    public static final io.restassured.specification.ResponseSpecification postByIdResponseSpec =
            BaseSpecs.baseResponseSpec;
}
*/
