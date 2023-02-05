package steps;

import io.qameta.allure.Step;
import org.exercise.ta.model.Ingredient;
import org.exercise.ta.model.Ingredients;
import org.hamcrest.Matcher;

import java.util.List;
import java.util.Map;

import static org.exercise.ta.helper.MessageHelper.getMsgFieldValue;
import static org.exercise.ta.helper.MessageHelper.getMsgIngField;
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
      assertThat(getMsgIngField(ing, "idIngredient"), ing.getIdIngredient(), notNullValue());
      assertThat(getMsgIngField(ing, "strIngredient"), ing.getStrIngredient(), notNullValue());
      assertThat(getMsgIngField(ing, "strDescription"), ing.getStrDescription(), notNullValue());
      assertThat(getMsgIngField(ing, "strType"), ing.getStrType(), notNullValue());
      assertThat(getMsgIngField(ing, "strABV"), ing.getStrABV(), matcher);
      if (isAlcohol) {
        assertEquals("Yes", ing.getStrAlcohol());
      } else {
        assertThat(getMsgIngField(ing, "strAlcohol"), ing.getStrAlcohol(), nullValue());
      }
    }
  }

  @Step("Assert required fields values are null or String")
  public void validateNotNullOrString(Map<String, String> map, String[] keys) {
    for (String key : keys) {
      assertTrue(getMsgFieldValue(key, map.get(key)), (map.get(key) == null || map.get(key) instanceof String));
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
