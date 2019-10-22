package com.funbluebits.cobra.entities;

import com.funbluebits.cobra.init.ModTileEntities;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BreakerTileEntity extends TileEntity implements ITickable, ICapabilityProvider {

  protected static final Logger LOGGER = LogManager.getLogger();

  public BreakerTileEntity(TileEntityType<?> tileEntityTypeIn) {
    super(tileEntityTypeIn);
    // TODO Auto-generated constructor stub
    LOGGER.info("BreakerTileEntity constructor params");
  }

  public BreakerTileEntity() {
    this(ModTileEntities.BREAKER_BLOCK);
    LOGGER.info("BreakerTileEntity constructor " + ModTileEntities.BREAKER_BLOCK);
  }

  @Override
  public void tick() {
    // TODO Auto-generated method stub
    LOGGER.info("BreakerTileEntity tick");
  }

  public void update() {
    // TODO Auto-generated method stub
    LOGGER.info("BreakerTileEntity update");
  }

  // Give it the capability of an inventory
  public static final int SIZE = 9;

  // This item handler will hold our nine inventory slots
  private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE) {
      @Override
      protected void onContentsChanged(int slot) {
          // We need to tell the tile entity that something has changed so
          // that the chest contents is persisted
          BreakerTileEntity.this.markDirty();
      }
  };

  @Override
  public void read(CompoundNBT compound) {
      super.read(compound);
      if (compound.contains("items")) {
          itemStackHandler.deserializeNBT((CompoundNBT) compound.get("items"));
      }
  }

  @Override
  public CompoundNBT write(CompoundNBT compound) {
      super.write(compound);
      compound.put("items", itemStackHandler.serializeNBT());
      return compound;
  }

  public boolean canInteractWith(PlayerEntity playerIn) {
      // If we are too far away from this tile entity you cannot use it
      //return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    return true;
  }

////  @Override
//  public boolean hasCapability(Capability<?> capability, Direction facing) {
//    if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
//      return true;
//    }
//    return super.hasCapability(capability, facing);
//  }
//  
////  @Override
//  public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
//      if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
//          //return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
//        return itemStackHandler;
//      }
//      return super.getCapability(capability, facing);
//  }  

  public void myTick() {
    LOGGER.info("BreakerTileEntity: myTick()");    
  }
}
