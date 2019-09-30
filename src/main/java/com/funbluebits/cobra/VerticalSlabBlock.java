package com.funbluebits.cobra;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class VerticalSlabBlock extends Block {
  protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);

  protected VerticalSlabBlock(Block.Properties properties) {
     super(properties);
  }

  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     return SHAPE;
  }

  /**
   * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
   * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
   */
  public BlockRenderLayer getRenderLayer() {
     return BlockRenderLayer.CUTOUT;
  }
}
