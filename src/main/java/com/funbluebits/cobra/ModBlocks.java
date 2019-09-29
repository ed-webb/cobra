package com.funbluebits.cobra;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

/**
 * This class has the register event handler for all custom blocks. This class uses @Mod.EventBusSubscriber so the event handler has to be static This class uses @ObjectHolder to get a reference to the blocks

  Based on tutorial here https://suppergerrie2.com/minecraft-1-14-modding-with-forge-5-custom-basic-block/
 */
@Mod.EventBusSubscriber(modid = CobraMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(CobraMod.MOD_ID)
public class ModBlocks {

    public static final Block chalk_block = null;

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

            new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE)).setRegistryName(CobraMod.MOD_ID, "chalk_block")

        );
    }

}