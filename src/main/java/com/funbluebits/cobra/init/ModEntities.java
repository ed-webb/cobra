package com.funbluebits.cobra.init;

import com.funbluebits.cobra.CobraMod;
import com.funbluebits.cobra.entities.SnakeEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ObjectHolder;

// TODO put this back in (without crashing) @ObjectHolder(CobraMod.MOD_ID)
public class ModEntities {

//  public static EntityType<?> green_snake = null; // TODO final 
  
  public static final EntityType<?> green_snake = EntityType.Builder.create(SnakeEntity::new, EntityClassification.CREATURE).build(CobraMod.MOD_ID + ":green_snake")
      .setRegistryName(CobraMod.MOD_ID, "green_snake");
  

}
