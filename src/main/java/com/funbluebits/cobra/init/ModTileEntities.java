package com.funbluebits.cobra.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.funbluebits.cobra.CobraMod;
import com.funbluebits.cobra.entities.BreakerTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CobraMod.MOD_ID)
public class ModTileEntities {

  protected static final Logger LOGGER = LogManager.getLogger();


  public static final TileEntityType<BreakerTileEntity> BREAKER_BLOCK = null; 
//      (TileEntityType<BreakerTileEntity>) TileEntityType.Builder.create(BreakerTileEntity::new, ModBlocks.BREAKER_BLOCK)
//      .build(null).setRegistryName(CobraMod.MOD_ID, "breaker_block");  

}
