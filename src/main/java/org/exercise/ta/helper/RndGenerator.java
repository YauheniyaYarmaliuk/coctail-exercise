package org.exercise.ta.helper;

import java.util.List;
import java.util.Random;

public class RndGenerator {

  public static String getRndLetter() {
    Random rnd = new Random();
    return String.valueOf((char) ('a' + rnd.nextInt(26)));
  }

  public static String getRndValueFromList(List<String> givenList) {
    Random rand = new Random();
    return givenList.get(rand.nextInt(givenList.size()));
  }
}
