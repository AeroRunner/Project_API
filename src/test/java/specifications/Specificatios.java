package specifications;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static herlpers.NewAllureListener.witchCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class Specificatios {

    public static RequestSpecification getUnknownUserReq = with()
            .filter(witchCustomTemplates())
            .log().body()
            .log().uri()
            .log().headers()
            .basePath("/api/unknown/23");
    public static ResponseSpecification getUnknownUserResp = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(STATUS)
            .log(BODY)
            .build();
    public static RequestSpecification updateUserReqSpec = with()
            .filter(witchCustomTemplates())
            .log().body()
            .log().uri()
            .log().headers()
            .contentType(ContentType.JSON)
            .basePath("/api/users/2");
    public static RequestSpecification registrationReqSpec = with()
            .filter(witchCustomTemplates())
            .log().body()
            .log().uri()
            .log().headers()
            .contentType(ContentType.JSON)
            .basePath("/api/register");
    public static ResponseSpecification registrationRespSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
    public static ResponseSpecification updateUserRespSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
    public static RequestSpecification createUserReqSpec = with()
            .filter(witchCustomTemplates())
            .log().body()
            .log().uri()
            .log().headers()
            .contentType(ContentType.JSON)
            .basePath("/api/users");
    public static ResponseSpecification createUserRespSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();
    public static RequestSpecification deleteUserReqSpec = with()
            .filter(witchCustomTemplates())
            .log().body()
            .log().uri()
            .log().headers()
            .basePath("/api/users/2");
    public static ResponseSpecification deleteuserRespSpec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .log(BODY)
            .build();
}