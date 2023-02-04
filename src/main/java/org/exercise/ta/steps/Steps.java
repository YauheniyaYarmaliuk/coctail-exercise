package org.exercise.ta.steps;

import io.restassured.response.Response;
import org.exercise.ta.model.Ingredient;
import org.exercise.ta.model.Ingredients;
import org.hamcrest.Matcher;

import java.util.List;
import java.util.Random;

import static org.exercise.ta.helper.CommonHelper.getMsg;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Steps {

  public String getRndValueFromList(List<String> givenList) {
    Random rand = new Random();
    return givenList.get(rand.nextInt(givenList.size()));
  }

  public void validateIngredients(Ingredients ingredients, boolean isAlcohol) {
    Matcher<Object> matcher = isAlcohol ? notNullValue() : nullValue();
    for (Ingredient ing : ingredients.getIngredients()) {
      assertThat(getMsg(ing, "idIngredient"), ing.getIdIngredient(), notNullValue());
      assertThat(getMsg(ing, "strIngredient"), ing.getStrIngredient(), notNullValue());
      assertThat(getMsg(ing, "strDescription"), ing.getStrDescription(), notNullValue());
      assertThat(getMsg(ing, "strType"), ing.getStrType(), notNullValue());
      assertThat(getMsg(ing, "strABV"), ing.getStrABV(), matcher);
      if (isAlcohol) {
        assertEquals("Yes", ing.getStrAlcohol());
      } else {
        assertThat(getMsg(ing, "strAlcohol"), ing.getStrAlcohol(), nullValue());
      }
    }
  }

  public void validateDrinks(Response drinks) {
    drinks.then().assertThat()
        .body("drinks.any { it.containsKey('strDrink') }", is(true))
        .body("drinks.any { it.containsKey('strTags') }", is(true))
        .body("drinks.any { it.containsKey('strCategory') }", is(true))
        .body("drinks.any { it.containsKey('strAlcoholic') }", is(true))
        .body("drinks.any { it.containsKey('strGlass') }", is(true))
        .body("drinks.any { it.containsKey('strInstructions') }", is(true))
        .body("drinks.any { it.containsKey('strIngredient1') }", is(true))
        .body("drinks.any { it.containsKey('strMeasure1') }", is(true))
        .body("drinks.any { it.containsKey('strCreativeCommonsConfirmed') }", is(true))
        .body("drinks.any { it.containsKey('dateModified') }", is(true));
  }

  public void validateFirstLetterFromList(List<String> strList, String firstLetter) {
    String msg = String.format("First letter: %s, Expect world: ", firstLetter);
    for (String sentence : strList) {
      assertTrue(msg + sentence, sentence.startsWith(firstLetter.toUpperCase()));
    }
  }
}
