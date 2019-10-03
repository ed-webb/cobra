package com.funbluebits.cobra;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.funbluebits.cobra.client.renders.CobraRenderRegistry;
import com.funbluebits.cobra.init.ModBlocks;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("cobra")
public class CobraMod
{
    public static final String MOD_ID = "cobra";
    
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public CobraMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
//        // Register the enqueueIMC method for modloading
//        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
//        // Register the processIMC method for modloading
//        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private static final BiomeManager.BiomeType[] biomeTypes = { BiomeManager.BiomeType.DESERT, BiomeManager.BiomeType.WARM, BiomeManager.BiomeType.COOL, BiomeManager.BiomeType.ICY };
    
    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        
        // Add the blocks that we want to be in the world generation as features of the biome
        int size = 7; // TODO Should this be more random?
        Placement<CountRangeConfig> placementIn = Placement.COUNT_RANGE;
        CountRangeConfig crc = new CountRangeConfig(10, 40, 0, 90);  // Vein/Chunk, MinHeight, MaxHeightBase, MaxHeight
        for (BiomeManager.BiomeType biomeType : biomeTypes) {
          for (BiomeManager.BiomeEntry entry : BiomeManager.getBiomes(biomeType)) {
            entry.biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,   
                Biome.createDecoratedFeature(Feature.ORE,
                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.chalk_block.getDefaultState(), size), placementIn, crc));
          }
        }
    }

    private void clientRegistries(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
        CobraRenderRegistry.registryEntityRenders();
    }

//    private void enqueueIMC(final InterModEnqueueEvent event)
//    {
//        // some example code to dispatch IMC to another mod
//        InterModComms.sendTo(MOD_ID, "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
//    }
//
//    private void processIMC(final InterModProcessEvent event)
//    {
//        // some example code to receive and process InterModComms from other mods
//        LOGGER.info("Got IMC {}", event.getIMCStream().
//                map(m->m.getMessageSupplier().get()).
//                collect(Collectors.toList()));
//    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

}
