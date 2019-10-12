package com.funbluebits.cobra.entities;

import com.funbluebits.cobra.init.ModTileEntities;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BreakerTileEntity extends TileEntity implements ITickable {

  protected static final Logger LOGGER = LogManager.getLogger();

  public BreakerTileEntity(TileEntityType<?> tileEntityTypeIn) {
    super(tileEntityTypeIn);
    // TODO Auto-generated constructor stub
  }

  public BreakerTileEntity() {
    this(ModTileEntities.BREAKER_ENTITY);
  }

  @Override
  public void tick() {
    // TODO Auto-generated method stub
    LOGGER.debug("BreakerTileEntity tick");
  }

  public void update() {
    // TODO Auto-generated method stub
    LOGGER.debug("BreakerTileEntity update");
  }

}
