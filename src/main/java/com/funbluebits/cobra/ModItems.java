package com.funbluebits.cobra;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SoupItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

/**
 * This class has the register event handler for all custom items.
 * This class uses @Mod.EventBusSubscriber so the event handler has to be static
 * This class uses @ObjectHolder to get a reference to the items
 */
@Mod.EventBusSubscriber(modid = CobraMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(CobraMod.MOD_ID)
public class ModItems {

    public static final Item chalk_dust = null;
    public static final Item oak_pole = null;
    public static final Item flesh_stew = null;
    public static final Item wooden_flute = null;
    
    private static final Food FOOD_FLESH_STEW = (new Food.Builder()).hunger(8).saturation(1.0F).meat().build();
    
    /**
     * The actual event handler that registers the custom items.
     *
     * @param event The event this event handler handles
     */
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //In here you pass in all item instances you want to register.
        //Make sure you always set the registry name.
        event.getRegistry().registerAll(

            // stackable to 64, add to Creative MISC tab, register the item.
             new Item(new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "chalk_dust")
            ,new BlockItem(ModBlocks.chalk_block, new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "chalk_block")
            ,new Item(new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "oak_pole")
            ,new BlockItem(ModBlocks.vertical_oak_slab, new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "vertical_oak_slab")
            ,new Item(new Item.Properties().maxStackSize(16).group(ItemGroup.MISC)).setRegistryName(CobraMod.MOD_ID, "wooden_flute")
            ,new Item((new Item.Properties()).group(ItemGroup.FOOD).food(FOOD_FLESH_STEW)).setRegistryName("flesh_stew")
        );
    }

}