package org.exercise.ta;

import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.exercise.ta.api.WebServiceApi;
import org.exercise.ta.model.Ingredients;
import org.exercise.ta.steps.Steps;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.exercise.ta.helper.CommonHelper.getRndLetter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.testng.AssertJUnit.assertEquals;

public class CocktailApiTest {
  final WebServiceApi api = new WebServiceApi();
  final Steps steps = new Steps();

  @BeforeTest
  public void setRequestSpec() {
    api.setRequestSpec();
  }

  @DataProvider(name = "values", parallel = true)
  public Object[][] values() {
    return new Object[][]{{"Non_Alcoholic", false}, {"Alcoholic", true}};
  }

  @Issue("Field strAlcohol get No exist null. Description should not be null")
  @Test(testName = "Check the fields using the search non/alcoholic ingredient", dataProvider = "values")
  public void checkFieldsBySearchNonAlcIng(String category, boolean isAlcohol) {
    List<String> cocktails = api.getCockNamesByCategory(category);
    String name = steps.getRndValueFromList(cocktails);
    List<String> ingNames = api.getFirstsIngByName(name);
    String ingredient = steps.getRndValueFromList(ingNames);
    Ingredients ingredients = api.getFieldsByIng(ingredient);
    steps.validateIngredients(ingredients, isAlcohol);
  }

  @Test(testName = "Verification does not exist cocktail via search")
  public void checkNotExistCocktailBySearch() {
    List<String> result = api.getDrinksByName("wrongCocktail");
    assertThat(String.format("Drinks is: %s", result), result, nullValue());
  }

  @Test(testName = "Check the required fields by using the search  case-insensitive Cocktail name")
  public void checkFieldsBySearchCockName() {
    String cockName = api.getRndCocktailName();
    Response drinks = api.getDrinksFieldsByName(cockName);
    steps.validateDrinks(drinks);
  }

  @Test(testName = "Check cocktail names by first letter")
  public void checkSearchResByFirstLetter() {
    String rndLetter = getRndLetter();
    List<String> cocktails = api.getCockNamesByFirstLetter(rndLetter);
    steps.validateFirstLetterFromList(cocktails, rndLetter);
  }

  @Test(testName = "Check popular filter query unregistered")
  public void checkPopularFilterWithoutReg() {
    String expMsg = "Only For Patreon supporters sorry";
    String cocktail = api.getPopularCocktail();
    assertEquals(cocktail, expMsg);
  }
}
