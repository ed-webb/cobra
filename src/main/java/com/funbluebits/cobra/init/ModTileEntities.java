package com.funbluebits.cobra.init;

import com.funbluebits.cobra.CobraMod;
import com.funbluebits.cobra.entities.BreakerTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CobraMod.MOD_ID)
public class ModTileEntities {

  public static final TileEntityType<BreakerTileEntity> BREAKER_ENTITY = (TileEntityType<BreakerTileEntity>) TileEntityType.Builder.create(BreakerTileEntity::new, ModBlocks.BREAKER_BLOCK)
      .build(null).setRegistryName(CobraMod.MOD_ID, "breaker_entity");  

}
