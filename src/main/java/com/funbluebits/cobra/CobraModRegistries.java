package com.funbluebits.cobra;

import com.funbluebits.cobra.blocks.BreakerBlock;
import com.funbluebits.cobra.blocks.SuperPistonBlock;
import com.funbluebits.cobra.blocks.VerticalSlabBlock;
import com.funbluebits.cobra.entities.SnakeEntity;
import com.funbluebits.cobra.init.ModBlocks;
import com.funbluebits.cobra.init.ModItems;
import com.funbluebits.cobra.init.ModTileEntities;
import com.funbluebits.cobra.items.CobraArmorMaterial;
import com.funbluebits.cobra.items.Foods;
import com.funbluebits.cobra.init.ModEntities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
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
        new BlockItem(ModBlocks.CHALK_BLOCK, new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "chalk_block"),
        new Item(new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "oak_pole"),
        new BlockItem(ModBlocks.VERTICAL_OAK_SLAB, new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "vertical_oak_slab"),
        new Item(new Item.Properties().maxStackSize(16).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "wooden_flute"),
        new Item((new Item.Properties()).group(ItemGroup.FOOD).food(Foods.FOOD_FLESH_STEW)).setRegistryName("flesh_stew"),
        new Item(new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "snake_scale"),
        new Item(new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "snake_meat"),
        new ArmorItem(CobraArmorMaterial.SNAKE, EquipmentSlotType.HEAD, (new Item.Properties()).group(ItemGroup.COMBAT)).setRegistryName(CobraMod.MOD_ID, "snake_helmet"),
        new ArmorItem(CobraArmorMaterial.SNAKE, EquipmentSlotType.CHEST, (new Item.Properties()).group(ItemGroup.COMBAT)).setRegistryName(CobraMod.MOD_ID, "snake_chestplate"),
        new ArmorItem(CobraArmorMaterial.SNAKE, EquipmentSlotType.LEGS, (new Item.Properties()).group(ItemGroup.COMBAT)).setRegistryName(CobraMod.MOD_ID, "snake_leggings"),
        new ArmorItem(CobraArmorMaterial.SNAKE, EquipmentSlotType.FEET, (new Item.Properties()).group(ItemGroup.COMBAT)).setRegistryName(CobraMod.MOD_ID, "snake_boots"),
        new BlockItem(ModBlocks.SUPER_PISTON, new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "super_piston"),
        new BlockItem(ModBlocks.SUPER_STICKY_PISTON, new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "super_sticky_piston"),
        new BlockItem(ModBlocks.BREAKER_BLOCK, new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "breaker_block")
        
      );
    RegisterEntities.registerEntitySpawnEggs(event);
  }

  @SubscribeEvent
  public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
    event.getRegistry().registerAll(
        ModEntities.GREEN_SNAKE
        );
    RegisterEntities.registerEntityWorldSpawns();
//    RegisterEntities.registerEntityAnimations();
  }

  @SubscribeEvent
  public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
    event.getRegistry().registerAll(
        ModTileEntities.BREAKER_ENTITY
        );
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
          new VerticalSlabBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.AXE)).setRegistryName(CobraMod.MOD_ID, "vertical_oak_slab"),
          new SuperPistonBlock(false, Block.Properties.create(Material.PISTON).hardnessAndResistance(0.5F).harvestLevel(1).harvestTool(ToolType.PICKAXE)).setRegistryName(CobraMod.MOD_ID, "super_piston"),
          new SuperPistonBlock(true, Block.Properties.create(Material.PISTON).hardnessAndResistance(0.5F).harvestLevel(1).harvestTool(ToolType.PICKAXE)).setRegistryName(CobraMod.MOD_ID, "super_sticky_piston"),
          new BreakerBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE)).setRegistryName(CobraMod.MOD_ID, "breaker_block")
      );
  }

}
