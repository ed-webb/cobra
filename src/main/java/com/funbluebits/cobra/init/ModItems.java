package com.funbluebits.cobra.init;

import com.funbluebits.cobra.CobraMod;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CobraMod.MOD_ID)
public class ModItems {

  public static final Item chalk_dust = null;
  public static final Item oak_pole = null;
  public static final Item flesh_stew = null;
  public static final Item wooden_flute = null;
  public static final Item chalk_block = null;
  public static final Item vertical_oak_slab = null;
  public static final Item green_snake_egg = null;
  
  public static final Food FOOD_FLESH_STEW = (new Food.Builder()).hunger(8).saturation(1.0F).meat().build();
}
