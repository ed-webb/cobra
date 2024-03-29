package com.funbluebits.cobra;

import com.funbluebits.cobra.entities.SnakeEntity;
import com.funbluebits.cobra.init.ModEntities;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;

public class RegisterEntities {

  private RegisterEntities() {
    // private constructor hides public one
  }

//  public static final EntityType<?> green_snake = EntityType.Builder.create(SnakeEntity::new, EntityClassification.CREATURE).build(CobraMod.MOD_ID + ":green_snake")
//      .setRegistryName(CobraMod.MOD_ID, "green_snake");
//
  public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event) {
    event.getRegistry().registerAll(
       registerEntitySpawnEgg(ModEntities.GREEN_SNAKE, 0x156b1a, 0xfaef1e, "green_snake_egg")   // green and yellow
        );
  }
  
  public static Item registerEntitySpawnEgg(EntityType<?> typeIn, int primaryColorIn, int secondaryColorIn, String name) {
    SpawnEggItem item = new SpawnEggItem(typeIn, primaryColorIn, secondaryColorIn, 
        new Item.Properties().group(ItemGroup.MISC));
    item.setRegistryName(CobraMod.MOD_ID, name);
    return item;
  }
  
  public static void registerEntityWorldSpawns() {
    registerEntityWorldSpawn(ModEntities.GREEN_SNAKE); // TODO change to monster??
    
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

  @OnlyIn(Dist.CLIENT)
  public static void registerEntityAnimations() {
    ResourceLocation location = new ResourceLocation(CobraMod.MOD_ID, "asms/entity/green_snake.json");
    ModelLoaderRegistry.loadASM(location, null);
  }
  
}
