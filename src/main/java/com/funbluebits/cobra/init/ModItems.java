package com.funbluebits.cobra.init;

import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ModItems {

  public static Item chalk_dust;
  public static Item oak_pole;
  public static Item flesh_stew;
  public static Item wooden_flute;
  public static Item chalk_block;
  public static Item vertical_oak_slab;
  public static Item green_snake_egg;
  
  public static final Food FOOD_FLESH_STEW = (new Food.Builder()).hunger(8).saturation(1.0F).meat().build();
}
