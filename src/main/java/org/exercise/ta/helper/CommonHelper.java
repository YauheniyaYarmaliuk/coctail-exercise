package org.exercise.ta.helper;

import org.exercise.ta.model.Ingredient;

import java.util.List;
import java.util.Random;

public class CommonHelper {

  public static String getMsgForIng(Ingredient ing, String field) {
    return String.format("Ing name: %s, Field: %s", ing.getStrIngredient(), field);
  }

  public static String getMsgExp(String field, String value) {
    return String.format("Field name: %s, Value: %s", field, value);
  }

  public static String getRndLetter() {
    Random rnd = new Random();
    return String.valueOf((char) ('a' + rnd.nextInt(26)));
  }

  public static String getRndValueFromList(List<String> givenList) {
    Random rand = new Random();
    return givenList.get(rand.nextInt(givenList.size()));
  }
}
