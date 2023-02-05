package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.exercise.ta.api.WebServiceApi;
import org.exercise.ta.model.Ingredients;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class ResponseParsingSteps {
  final WebServiceApi api = new WebServiceApi();

  @Step("Get list cocktail names by filter Category")
  public List<String> getCocktailNamesByCategory(String alcCategory) {
    Response response = api.getCocktailsByCategory(alcCategory);
    return response.jsonPath().getList("drinks.strDrink");
  }

  @Step("Get first ingredient by cocktail name")
  public List<String> getFirstsIngByName(String cocktailName) {
    Response response = api.getSearchByName(cocktailName);
    return response.jsonPath().getList("drinks.strIngredient1");
  }

  @Step("Get Drinks fields by Cocktail Name")
  public Map<String, String> getDrinksByName(String cocktailName) {
    Response response = api.getSearchByName(cocktailName);
    return response.jsonPath().getMap("drinks[0]");
  }

  @Step("Get Ingredient fields by ingredient")
  public Ingredients getFieldsByIng(String ingredient) {
    Response response = api.getSearchByIngredient(ingredient);
    return response.body().as(Ingredients.class);
  }

  @Step("Get random cocktail name")
  public String getRndCocktailName() {
    Response response = api.getRndCocktail();
    return response.jsonPath().getString("drinks.strDrink[0]");
  }

  @Step("Get cocktails names by searching via first letter")
  public List<String> getCocktailNamesByFirstLetter(String letter) {
    String warning = "Nothing was found by letter: ";
    Response response = api.getSearchByFirstLetter(letter);
    assertThat(warning + letter, response.jsonPath(), nullValue());
    return response.jsonPath().getList("drinks.strDrink");
  }

  @Step("Get a popular cocktail name")
  public String getNameOfPopularCocktail() {
    Response response = api.getPopularCocktail();
    return response.jsonPath().getString("drinks.strDrink");
  }
}
