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

    public static Item green_snake_egg = null;
    
}