package com.funbluebits.cobra;

import com.funbluebits.cobra.blocks.VerticalSlabBlock;
import com.funbluebits.cobra.entities.SnakeEntity;
import com.funbluebits.cobra.init.ModBlocks;
import com.funbluebits.cobra.init.ModItems;
import com.funbluebits.cobra.items.Foods;
import com.funbluebits.cobra.init.ModEntities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class CobraModRegistries {

  @SubscribeEvent
  public static void registerItems(final RegistryEvent.Register<Item> event)
  {
    event.getRegistry().registerAll (
        
        // stackable to 64, add to Creative MISC tab, register the item.
        new Item(new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "chalk_dust"),
        new BlockItem(ModBlocks.chalk_block, new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "chalk_block"),
        new Item(new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "oak_pole"),
        new BlockItem(ModBlocks.vertical_oak_slab, new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "vertical_oak_slab"),
        new Item(new Item.Properties().maxStackSize(16).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "wooden_flute"),
        new Item((new Item.Properties()).group(ItemGroup.FOOD).food(Foods.FOOD_FLESH_STEW)).setRegistryName("flesh_stew")
        
      );
    RegisterEntities.registerEntitySpawnEggs(event);
  }

  @SubscribeEvent
  public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
    event.getRegistry().registerAll(
        EntityType.Builder.create(SnakeEntity::new, EntityClassification.CREATURE).build(CobraMod.MOD_ID + ":green_snake")
        .setRegistryName(CobraMod.MOD_ID, "green_snake")
        );
    RegisterEntities.registerEntityWorldSpawns();
  }

  /**
   * The actual event handler that registers the custom blocks.
   *
   * @param event The event this event handler handles
   */
  @SubscribeEvent
  public static void registerBlocks(final RegistryEvent.Register<Block> event) {
      //In here you pass in all block instances you want to register.
      //Make sure you always set the registry name.
      event.getRegistry().registerAll(

          new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE)).setRegistryName(CobraMod.MOD_ID, "chalk_block"),
          new VerticalSlabBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.AXE)).setRegistryName(CobraMod.MOD_ID, "vertical_oak_slab")

      );
  }

}
