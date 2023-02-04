package org.exercise.ta.helper;

import org.exercise.ta.model.Ingredient;

import java.util.Random;

public class CommonHelper {

  public static String getMsg(Ingredient ing, String field) {
    return String.format("Ing name: %s, Field: %s", ing.getStrIngredient(), field);
  }

  public static String getRndLetter() {
    Random rnd = new Random();
    return String.valueOf((char) ('a' + rnd.nextInt(26)));
  }
}
