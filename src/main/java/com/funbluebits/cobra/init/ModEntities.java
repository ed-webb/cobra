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

@ObjectHolder(CobraMod.MOD_ID)
public class ModEntities {

  private ModEntities() {
    // Private constructor hides public one.
  }
  
  public static final EntityType<?> green_snake = null;
  
  public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event) {
    event.getRegistry().registerAll(
       registerEntitySpawnEgg(green_snake, 0x156b1a, 0xfaef1e, "green_snake_egg")   // green and yellow
        );
  }
  
  public static Item registerEntitySpawnEgg(EntityType<?> typeIn, int primaryColorIn, int secondaryColorIn, String name) {
    SpawnEggItem item = new SpawnEggItem(typeIn, primaryColorIn, secondaryColorIn, 
        new Item.Properties().group(ItemGroup.MISC));
    item.setRegistryName(CobraMod.MOD_ID, name);
    return item;
  }
  
  public static void registerEntityWorldSpawns() {
    registerEntityWorldSpawn(green_snake); // TODO change to monster??
    
  }
  
  // { BiomeManager.BiomeType.DESERT, BiomeManager.BiomeType.WARM, BiomeManager.BiomeType.COOL, BiomeManager.BiomeType.ICY };
  
  // Only put snakes in hotter climates.
  private static final BiomeManager.BiomeType[] snakeBiomeTypes = { BiomeManager.BiomeType.DESERT, BiomeManager.BiomeType.WARM };

  public static void registerEntityWorldSpawn(EntityType<?> entity) {
    for (BiomeManager.BiomeType biomeType : snakeBiomeTypes) {
      for (BiomeManager.BiomeEntry entry : BiomeManager.getBiomes(biomeType)) {
        if (entry.biome != null) {
          entry.biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, 10, 1, 5));  // weight, minGroupCount, maxGroupCount
        }
      }
    }
    
  }


}
