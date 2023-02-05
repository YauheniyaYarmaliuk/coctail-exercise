package org.exercise.ta;

import io.qameta.allure.Issue;
import org.exercise.ta.model.Ingredients;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.ResponseParsingSteps;
import steps.ValidationSteps;

import java.util.List;
import java.util.Map;

import static org.exercise.ta.helper.RndGenerator.getRndLetter;
import static org.exercise.ta.helper.RndGenerator.getRndValueFromList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.testng.AssertJUnit.assertEquals;

public class CocktailApiTest {
  final ResponseParsingSteps steps = new ResponseParsingSteps();
  final ValidationSteps validSteps = new ValidationSteps();

  @DataProvider(name = "values", parallel = true)
  public Object[][] values() {
    return new Object[][]{
        {"Non_Alcoholic", false},
        {"Alcoholic", true}
    };
  }

  @Issue("Field strAlcohol get 'No' exist null. Description should not be null")
  @Test(testName = "Check the fields using the search non/alcoholic ingredient", dataProvider = "values")
  public void checkFieldsBySearchNonAlcIng(String category, boolean isAlcohol) {
    List<String> cocktails = steps.getCocktailNamesByCategory(category);
    String name = getRndValueFromList(cocktails);
    List<String> ingNames = steps.getFirstsIngByName(name);
    String ingredient = getRndValueFromList(ingNames);
    Ingredients ingredients = steps.getFieldsByIng(ingredient);
    validSteps.validateIngredientFields(ingredients, isAlcohol);
  }

  @Test(testName = "Verification does not exist cocktail via search")
  public void checkNotExistCocktailBySearch() {
    String wrongName = "wrongCocktail";
    Map<String, String> drinks = steps.getDrinksByName(wrongName);
    assertThat(drinks, nullValue());
  }

  @Test(testName = "Check the required fields by using the search  case-insensitive Cocktail name")
  public void checkFieldsBySearchCocktailName() {
    String[] requiredFields = {"strDrink", "strTags", "strCategory",
        "strAlcoholic", "strGlass", "strIngredient1", "strMeasure1",
        "strCreativeCommonsConfirmed", "dateModified"};
    String cocktailName = steps.getRndCocktailName();
    Map<String, String> drinks = steps.getDrinksByName(cocktailName);
    validSteps.validateNotNullOrString(drinks, requiredFields);
  }

  @Test(testName = "Check cocktail names by first letter")
  public void checkSearchResByFirstLetter() {
    String rndLetter = getRndLetter();
    List<String> cocktails = steps.getCocktailNamesByFirstLetter(rndLetter);
    validSteps.validateFirstLetterFromList(cocktails, rndLetter);
  }

  @Test(testName = "Check popular filter query unregistered")
  public void checkPopularFilterWithoutReg() {
    String expMsg = "Only For Patreon supporters sorry";
    String actMsg = steps.getNameOfPopularCocktail();
    assertEquals(actMsg, expMsg);
  }
}
