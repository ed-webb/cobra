package com.funbluebits.cobra.blocks;

import com.funbluebits.cobra.entities.BreakerTileEntity;

// inventory and GUI - see https://www.youtube.com/watch?v=K96bq34Semc&list=PLmaTwVFUUXiBE7m5d_uLIRyujqsPTYX4C&index=5

import net.minecraft.block.Block;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.minecraftforge.common.extensions.IForgeBlockState;

public class BreakerBlock extends HorizontalBlock implements IForgeBlock, ITileEntityProvider {

  public BreakerBlock(Block.Properties builder) {
    super(builder);
  }

  public TileEntity createTileEntity(World world, IForgeBlockState state) {
    // TODO Auto-generated method stub
    return new BreakerTileEntity();
  }
  
  @Deprecated //Forge: New State sensitive version.
  public boolean hasTileEntity() {
     return true;
  }

  @Override
  public TileEntity createNewTileEntity(IBlockReader worldIn) {
    // TODO Auto-generated method stub
    return new BreakerTileEntity();
  }

  
}
