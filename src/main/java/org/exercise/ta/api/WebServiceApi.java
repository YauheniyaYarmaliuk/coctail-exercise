package org.exercise.ta.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exercise.ta.model.Ingredients;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.exercise.ta.data.Urls.*;

public class WebServiceApi {

  private final Logger logger = LogManager.getLogger(WebServiceApi.class);
  RequestSpecification requestSpec;

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

  public List<String> getCockNamesByCategory(String alcCategory) {
    logger.info("Get Cocktail's names by alc filter via category: " + alcCategory);
    Response res = given().spec(requestSpec)
        .pathParam("category", alcCategory)
        .when().get(FILTER_BY_ALC);
    return getExtResponse(res).jsonPath().getList("drinks.strDrink");
  }

  public List<String> getFirstsIngByName(String cockName) {
    Response response = getDrinksFieldsByName(cockName);
    return getExtResponse(response).jsonPath().getList("drinks.strIngredient1");
  }

  public List<String> getDrinksByName(String cockName) {
    Response response = getDrinksFieldsByName(cockName);
    return getExtResponse(response).jsonPath().getList("drinks");
  }

  public Response getDrinksFieldsByName(String cockName) {
    logger.info("Get Drink fields by cockName: " + cockName);
    return given().spec(requestSpec)
        .pathParam("name", cockName)
        .when().get(SEARCH_BY_NAME);
  }

  public Ingredients getFieldsByIng(String ingredient) {
    logger.info("Get Ingredients Fields by ingredient: " + ingredient);
    Response res = given().spec(requestSpec)
        .pathParam("ing", ingredient)
        .when().get(SEARCH_BY_ING);
    return getExtResponse(res).body().as(Ingredients.class);
  }

  public String getRndCocktailName() {
    logger.info("Get rnd Cocktail name");
    Response response = given().spec(requestSpec)
        .when().get(RND_COCKTAIL);
    return getExtResponse(response).jsonPath().getString("drinks.strDrink[0]");
  }

  public List<String> getCockNamesByFirstLetter(String letter) {
    logger.info("Get Cocktail names by searching first letter " + letter);
    Response response = given().spec(requestSpec)
        .pathParam("letter", letter)
        .when().get(SEARCH_BY_FIRST_L);
    return getExtResponse(response).jsonPath().getList("drinks.strDrink");
  }

  public String getPopularCocktail() {
    logger.info("Get popular cocktail");
    Response response = given().spec(requestSpec)
        .when().get(POPULAR_COCKTAIL);
    return getExtResponse(response).jsonPath().getString("drinks.strDrink");
  }

  private Response getExtResponse(Response response) {
    return response
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_OK)
        .extract().response();
  }
}
