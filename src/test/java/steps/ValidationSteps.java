package steps;

import io.qameta.allure.Step;
import org.exercise.ta.model.Ingredient;
import org.exercise.ta.model.Ingredients;
import org.hamcrest.Matcher;

import java.util.List;
import java.util.Map;

import static org.exercise.ta.helper.CommonHelper.getMsgExp;
import static org.exercise.ta.helper.CommonHelper.getMsgForIng;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ValidationSteps {

  @Step("Assert fields values from Ingredient model for Alcoholic and non Alc group")
  public void validateIngredientFields(Ingredients ingredients, boolean isAlcohol) {
    Matcher<Object> matcher = isAlcohol ? notNullValue() : nullValue();
    for (Ingredient ing : ingredients.getIngredients()) {
      assertThat(getMsgForIng(ing, "idIngredient"), ing.getIdIngredient(), notNullValue());
      assertThat(getMsgForIng(ing, "strIngredient"), ing.getStrIngredient(), notNullValue());
      assertThat(getMsgForIng(ing, "strDescription"), ing.getStrDescription(), notNullValue());
      assertThat(getMsgForIng(ing, "strType"), ing.getStrType(), notNullValue());
      assertThat(getMsgForIng(ing, "strABV"), ing.getStrABV(), matcher);
      if (isAlcohol) {
        assertEquals("Yes", ing.getStrAlcohol());
      } else {
        assertThat(getMsgForIng(ing, "strAlcohol"), ing.getStrAlcohol(), nullValue());
      }
    }
  }

  @Step("Assert required fields values are null or String")
  public void validateNotNullOrString(Map<String, String> map, String[] keys) {
    for (String key : keys) {
      assertTrue(getMsgExp(key, map.get(key)), (map.get(key) == null || map.get(key) instanceof String));
    }
  }

  @Step("Assert result of searching by filling first letter")
  public void validateFirstLetterFromList(List<String> strList, String firstLetter) {
    String msg = String.format("First letter: %s, Expect world: ", firstLetter);
    for (String sentence : strList) {
      assertTrue(msg + sentence, sentence.startsWith(firstLetter.toUpperCase()));
    }
  }
}
