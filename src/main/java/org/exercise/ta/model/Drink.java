package org.exercise.ta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Drink {
  @JsonProperty(required = true)
  String strDrink;
  @JsonProperty(required = true)
  String strTags;
  @JsonProperty(required = true)
  String strCategory;
  @JsonProperty(required = true)
  String strAlcoholic;
  @JsonProperty(required = true)
  String strGlass;
  @JsonProperty(required = true)
  String strIngredient1;
  @JsonProperty(required = true)
  String strMeasure1;
  @JsonProperty(required = true)
  String strCreativeCommonsConfirmed;
  @JsonProperty(required = true)
  String dateModified;
  String strDrinkAlternative;
  String strVideo;
  String strIBA;
  String strDrinkThumb;
  String strImageSource;
  String strImageAttribution;

  public String getStrDrink() {
    return strDrink;
  }

  public void setStrDrink(String strDrink) {
    this.strDrink = strDrink;
  }

  public String getStrTags() {
    return strTags;
  }

  public void setStrTags(String strTags) {
    this.strTags = strTags;
  }

  public String getStrCategory() {
    return strCategory;
  }

  public void setStrCategory(String strCategory) {
    this.strCategory = strCategory;
  }

  public String getStrAlcoholic() {
    return strAlcoholic;
  }

  public void setStrAlcoholic(String strAlcoholic) {
    this.strAlcoholic = strAlcoholic;
  }

  public String getStrGlass() {
    return strGlass;
  }

  public void setStrGlass(String strGlass) {
    this.strGlass = strGlass;
  }

  public String getStrIngredient1() {
    return strIngredient1;
  }

  public void setStrIngredient1(String strIngredient1) {
    this.strIngredient1 = strIngredient1;
  }

  public String getStrMeasure1() {
    return strMeasure1;
  }

  public void setStrMeasure1(String strMeasure1) {
    this.strMeasure1 = strMeasure1;
  }

  public String getStrCreativeCommonsConfirmed() {
    return strCreativeCommonsConfirmed;
  }

  public void setStrCreativeCommonsConfirmed(String strCreativeCommonsConfirmed) {
    this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
  }

  public String getDateModified() {
    return dateModified;
  }

  public void setDateModified(String dateModified) {
    this.dateModified = dateModified;
  }

  public String getStrDrinkAlternative() {
    return strDrinkAlternative;
  }

  public void setStrDrinkAlternative(String strDrinkAlternative) {
    this.strDrinkAlternative = strDrinkAlternative;
  }

  public String getStrVideo() {
    return strVideo;
  }

  public void setStrVideo(String strVideo) {
    this.strVideo = strVideo;
  }

  public String getStrIBA() {
    return strIBA;
  }

  public void setStrIBA(String strIBA) {
    this.strIBA = strIBA;
  }

  public String getStrDrinkThumb() {
    return strDrinkThumb;
  }

  public void setStrDrinkThumb(String strDrinkThumb) {
    this.strDrinkThumb = strDrinkThumb;
  }

  public String getStrImageSource() {
    return strImageSource;
  }

  public void setStrImageSource(String strImageSource) {
    this.strImageSource = strImageSource;
  }

  public String getStrImageAttribution() {
    return strImageAttribution;
  }

  public void setStrImageAttribution(String strImageAttribution) {
    this.strImageAttribution = strImageAttribution;
  }
}