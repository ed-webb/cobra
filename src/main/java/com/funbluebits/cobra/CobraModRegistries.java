package com.funbluebits.cobra;

import com.funbluebits.cobra.blocks.VerticalSlabBlock;
import com.funbluebits.cobra.init.ModBlocks;
import com.funbluebits.cobra.init.ModItems;
import com.funbluebits.cobra.init.ModEntities;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class CobraModRegistries {

  public CobraModRegistries() {
    // TODO Auto-generated constructor stub
  }

  @SubscribeEvent
  public static void registerItems(final RegistryEvent.Register<Item> event)
  {
    event.getRegistry().registerAll (
        
        // stackable to 64, add to Creative MISC tab, register the item.
       ModItems.chalk_dust = new Item(new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "chalk_dust"),
       ModItems.chalk_block = new BlockItem(ModBlocks.chalk_block, new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "chalk_block"),
       ModItems.oak_pole = new Item(new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "oak_pole"),
       ModItems.vertical_oak_slab = new BlockItem(ModBlocks.vertical_oak_slab, new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "vertical_oak_slab"),
       ModItems.wooden_flute = new Item(new Item.Properties().maxStackSize(16).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "wooden_flute"),
       ModItems.flesh_stew = new Item((new Item.Properties()).group(ItemGroup.FOOD).food(ModItems.FOOD_FLESH_STEW)).setRegistryName("flesh_stew")
        
        );
    ModEntities.registerEntitySpawnEggs(event);
  }

  @SubscribeEvent
  public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
    event.getRegistry().registerAll(
        ModEntities.green_snake
        );
    ModEntities.registerEntityWorldSpawns();
  }

  /**
   * The actual event handler that registers the custom blocks.
   *
   * @param event The event this event handler handles
   */
  @SubscribeEvent
  public static void registerBlocks(RegistryEvent.Register<Block> event) {
      //In here you pass in all block instances you want to register.
      //Make sure you always set the registry name.
      event.getRegistry().registerAll(

          ModBlocks.chalk_block = new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE)).setRegistryName(CobraMod.MOD_ID, "chalk_block"),
          ModBlocks.vertical_oak_slab = new VerticalSlabBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.AXE)).setRegistryName(CobraMod.MOD_ID, "vertical_oak_slab")

      );
  }

}
