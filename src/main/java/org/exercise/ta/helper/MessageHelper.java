package org.exercise.ta.helper;

import org.exercise.ta.model.Ingredient;

public class MessageHelper {

  public static String getMsgIngField(Ingredient ing, String field) {
    return String.format("Ing name: %s, Field: %s", ing.getStrIngredient(), field);
  }

  public static String getMsgFieldValue(String field, String value) {
    return String.format("Field name: %s, Value: %s", field, value);
  }
}
