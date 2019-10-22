package com.funbluebits.cobra.items;

import net.minecraft.item.Food;

public class Foods {

  public static final Food FOOD_FLESH_STEW = (new Food.Builder()).hunger(8).saturation(1.0F).meat().build();
  public static final Food CABBAGE = (new Food.Builder()).hunger(2).saturation(0.3F).build();
  public static final Food CHICKEN_SOUP = (new Food.Builder()).hunger(8).saturation(1.2F).meat().build();
  public static final Food FISH_SOUP = (new Food.Builder()).hunger(8).saturation(1.2F).build();
  public static final Food VEGETABLE_SOUP = (new Food.Builder()).hunger(8).saturation(1.0F).build();
}
