package org.exercise.ta.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.exercise.ta.data.Urls.*;

public class WebServiceApi {
  private final Logger logger = LogManager.getLogger(WebServiceApi.class);
  RequestSpecification requestSpec;

  public WebServiceApi() {
    setRequestSpec();
  }

  public static RequestSpecBuilder getHeader() {
    return new RequestSpecBuilder()
        .addHeader("Accept", "application/json")
        .addHeader("Content-Type", "application/json");
  }

  public void setRequestSpec() {
    logger.info("Add Header");
    RestAssured.baseURI = BASE_URL;
    RestAssured.basePath = BASE_PATH;
    requestSpec = getHeader().build();
  }

  public Response getCocktailsByCategory(String alcCategory) {
    logger.info("Get Cocktail's names by alc filter via category: " + alcCategory);
    Response res = given().spec(requestSpec)
        .pathParam("category", alcCategory)
        .when().get(FILTER_BY_ALC);
    return getExtResponse(res);
  }

  public Response getSearchByIngredient(String ingredient) {
    logger.info("Get Ingredients Fields by ingredient: " + ingredient);
    Response res = given().spec(requestSpec)
        .pathParam("ing", ingredient)
        .when().get(SEARCH_BY_ING);
    return getExtResponse(res);
  }

  public Response getRndCocktail() {
    logger.info("Get rnd Cocktail name");
    Response response = given().spec(requestSpec)
        .when().get(RND_COCKTAIL);
    return getExtResponse(response);
  }

  public Response getSearchByFirstLetter(String letter) {
    logger.info("Get Cocktail names by searching first letter " + letter);
    Response response = given().spec(requestSpec)
        .pathParam("letter", letter)
        .when().get(SEARCH_BY_FIRST_L);
    return getExtResponse(response);
  }

  public Response getPopularCocktail() {
    logger.info("Get popular cocktail");
    Response response = given().spec(requestSpec)
        .when().get(POPULAR_COCKTAIL);
    return getExtResponse(response);
  }

  public Response getSearchByName(String cocktailName) {
    logger.info("Get Drink fields by cocktailName: " + cocktailName);
    Response res = given().spec(requestSpec)
        .pathParam("name", cocktailName)
        .when().get(SEARCH_BY_NAME);
    return getExtResponse(res);
  }

  private Response getExtResponse(Response response) {
    return response.then().assertThat()
        .statusCode(HttpStatus.SC_OK)
        .log().ifValidationFails()
        .extract().response();
  }
}
