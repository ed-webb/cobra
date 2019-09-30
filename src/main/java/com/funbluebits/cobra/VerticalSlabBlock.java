package com.funbluebits.cobra;

/**
 * Unlike horizontal slabs which have a top and bottom version, these face a direction, being placed in the same direction as the player
 * The rear face of the block is the half filled with plank, so when it is facing:north, the north side has air in it.
 */
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class VerticalSlabBlock extends HorizontalBlock implements IWaterLoggable {
  public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
  public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

  protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);

  protected VerticalSlabBlock(Block.Properties properties) {
     super(properties);
     this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
  }

  /**
   * @deprecated
   */
  @Override
  @Deprecated
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
     return SHAPE;
  }

  @Override
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
  }

  @Override
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(FACING, WATERLOGGED);
  }

  /**
   * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
   * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
   * 
   * Without this, blocks placed next to it cull their adjacent faces and we can see through the world.
   */
  @Override
  public BlockRenderLayer getRenderLayer() {
     return BlockRenderLayer.CUTOUT;
  }

  /**
   * @deprecated
   */
  @Override
  @Deprecated
  public IFluidState getFluidState(BlockState state) {
    return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
 }

  @Override
  public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
    return IWaterLoggable.super.receiveFluid(worldIn, pos, state, fluidStateIn);
  }

  @Override
  public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
    return IWaterLoggable.super.canContainFluid(worldIn, pos, state, fluidIn);
  }

 /**
  * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
  * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
  * returns its solidified counterpart.
  * Note that this method should ideally consider only the specific face passed in.
  * @deprecated
  */
  @Override
  @Deprecated
  public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
    if (stateIn.get(WATERLOGGED)) {
       worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
    }

    return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
  }

  /**
   * @deprecated
   */
  @Override
  @Deprecated
  public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
    switch(type) {
    case LAND:
       return false;
    case WATER:
       return worldIn.getFluidState(pos).isTagged(FluidTags.WATER);
    case AIR:
       return false;
    default:
       return false;
    }
  }
}
