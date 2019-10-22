package com.funbluebits.cobra.blocks;

import java.util.List;
import java.util.Set;

import com.funbluebits.cobra.blocks.state.SuperPistonBlockStructureHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MovingPistonBlock;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.PistonHeadBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.block.state.PistonBlockStructureHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.PistonType;
import net.minecraft.tileentity.PistonTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SuperPistonBlock extends PistonBlock {

  private final boolean isSticky;

  public SuperPistonBlock(boolean sticky, Properties properties) {
    super(sticky, properties);
    this.isSticky = sticky;

  }

  // These 3 methods are overridden so that we can use our own copy of checkForMove
  /**
   * Called by ItemBlocks after a block is set in the world, to allow post-place logic
   */
  public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
     if (!worldIn.isRemote) {
        this.checkForMoveSuper(worldIn, pos, state);
     }

  }

  public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
     if (!worldIn.isRemote) {
        this.checkForMoveSuper(worldIn, pos, state);
     }

  }

  public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
     if (oldState.getBlock() != state.getBlock()) {
        if (!worldIn.isRemote && worldIn.getTileEntity(pos) == null) {
           this.checkForMoveSuper(worldIn, pos, state);
        }

     }
  }

  private void checkForMoveSuper(World worldIn, BlockPos pos, BlockState state) {
    Direction direction = state.get(FACING);
    boolean flag = this.shouldBeExtended(worldIn, pos, direction);
    if (flag && !state.get(EXTENDED)) {
       if ((new SuperPistonBlockStructureHelper(worldIn, pos, direction, true)).canMove()) {
          worldIn.addBlockEvent(pos, this, 0, direction.getIndex());
       }
    } else if (!flag && state.get(EXTENDED)) {
       BlockPos blockpos = pos.offset(direction, 2);
       BlockState blockstate = worldIn.getBlockState(blockpos);
       int i = 1;
       if (blockstate.getBlock() == Blocks.MOVING_PISTON && blockstate.get(FACING) == direction) {
          TileEntity tileentity = worldIn.getTileEntity(blockpos);
          if (tileentity instanceof PistonTileEntity) {
             PistonTileEntity pistontileentity = (PistonTileEntity)tileentity;
             if (pistontileentity.isExtending() && (pistontileentity.getProgress(0.0F) < 0.5F || worldIn.getGameTime() == pistontileentity.getLastTicked() || ((ServerWorld)worldIn).isInsideTick())) {
                i = 2;
             }
          }
       }

       worldIn.addBlockEvent(pos, this, i, direction.getIndex());
    }

 }

  private boolean shouldBeExtended(World worldIn, BlockPos pos, Direction facing) {
    for(Direction direction : Direction.values()) {
       if (direction != facing && worldIn.isSidePowered(pos.offset(direction), direction)) {
          return true;
       }
    }

    if (worldIn.isSidePowered(pos, Direction.DOWN)) {
       return true;
    } else {
       BlockPos blockpos = pos.up();

       for(Direction direction1 : Direction.values()) {
          if (direction1 != Direction.DOWN && worldIn.isSidePowered(blockpos.offset(direction1), direction1)) {
             return true;
          }
       }

       return false;
    }
 }

  /**
   * Called on server when World#addBlockEvent is called. If server returns true, then also called on the client. On
   * the Server, this may perform additional changes to the world, like pistons replacing the block with an extended
   * base. On the client, the update may involve replacing tile entities or effects such as sounds or particles
   * @deprecated call via {@link IBlockState#onBlockEventReceived(World,BlockPos,int,int)} whenever possible.
   * Implementing/overriding is fine.
   */
  public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
     Direction direction = state.get(FACING);
     if (!worldIn.isRemote) {
        boolean flag = this.shouldBeExtended(worldIn, pos, direction);
        if (flag && (id == 1 || id == 2)) {
           worldIn.setBlockState(pos, state.with(EXTENDED, Boolean.valueOf(true)), 2);
           return false;
        }

        if (!flag && id == 0) {
           return false;
        }
     }

     if (id == 0) {
        if(net.minecraftforge.event.ForgeEventFactory.onPistonMovePre(worldIn, pos, direction, true)) return false;
        if (!this.doMove(worldIn, pos, direction, true)) {
           return false;
        }

        worldIn.setBlockState(pos, state.with(EXTENDED, Boolean.valueOf(true)), 67);
        worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.25F + 0.6F);
     } else if (id == 1 || id == 2) {
         if(net.minecraftforge.event.ForgeEventFactory.onPistonMovePre(worldIn, pos, direction, false)) return false;
        TileEntity tileentity1 = worldIn.getTileEntity(pos.offset(direction));
        if (tileentity1 instanceof PistonTileEntity) {
           ((PistonTileEntity)tileentity1).clearPistonTileEntity();
        }

        worldIn.setBlockState(pos, Blocks.MOVING_PISTON.getDefaultState().with(MovingPistonBlock.FACING, direction).with(MovingPistonBlock.TYPE, this.isSticky ? PistonType.STICKY : PistonType.DEFAULT), 3);
        worldIn.setTileEntity(pos, MovingPistonBlock.createTilePiston(this.getDefaultState().with(FACING, Direction.byIndex(param & 7)), direction, false, true));
        if (this.isSticky) {
           BlockPos blockpos = pos.add(direction.getXOffset() * 2, direction.getYOffset() * 2, direction.getZOffset() * 2);
           BlockState blockstate = worldIn.getBlockState(blockpos);
           Block block = blockstate.getBlock();
           boolean flag1 = false;
           if (block == Blocks.MOVING_PISTON) {
              TileEntity tileentity = worldIn.getTileEntity(blockpos);
              if (tileentity instanceof PistonTileEntity) {
                 PistonTileEntity pistontileentity = (PistonTileEntity)tileentity;
                 if (pistontileentity.getFacing() == direction && pistontileentity.isExtending()) {
                    pistontileentity.clearPistonTileEntity();
                    flag1 = true;
                 }
              }
           }

           if (!flag1) {
              if (id != 1 || blockstate.isAir(worldIn, blockpos) || !canPush(blockstate, worldIn, blockpos, direction.getOpposite(), false, direction) || blockstate.getPushReaction() != PushReaction.NORMAL && block != Blocks.PISTON && block != Blocks.STICKY_PISTON) {
                 worldIn.removeBlock(pos.offset(direction), false);
              } else {
                 this.doMove(worldIn, pos, direction, false);
              }
           }
        } else {
           worldIn.removeBlock(pos.offset(direction), false);
        }

        worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.15F + 0.6F);
     }

     net.minecraftforge.event.ForgeEventFactory.onPistonMovePost(worldIn, pos, direction, (id == 0));
     return true;
  }

  private boolean doMove(World worldIn, BlockPos pos, Direction directionIn, boolean extending) {
    BlockPos blockpos = pos.offset(directionIn);
    if (!extending && worldIn.getBlockState(blockpos).getBlock() == Blocks.PISTON_HEAD) {
       worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 20);
    }

    PistonBlockStructureHelper pistonblockstructurehelper = new PistonBlockStructureHelper(worldIn, pos, directionIn, extending);
    if (!pistonblockstructurehelper.canMove()) {
       return false;
    } else {
       List<BlockPos> list = pistonblockstructurehelper.getBlocksToMove();
       List<BlockState> list1 = Lists.newArrayList();

       for(int i = 0; i < list.size(); ++i) {
          BlockPos blockpos1 = list.get(i);
          list1.add(worldIn.getBlockState(blockpos1));
       }

       List<BlockPos> list2 = pistonblockstructurehelper.getBlocksToDestroy();
       int k = list.size() + list2.size();
       BlockState[] ablockstate = new BlockState[k];
       Direction direction = extending ? directionIn : directionIn.getOpposite();
       Set<BlockPos> set = Sets.newHashSet(list);

       for(int j = list2.size() - 1; j >= 0; --j) {
          BlockPos blockpos2 = list2.get(j);
          BlockState blockstate = worldIn.getBlockState(blockpos2);
          TileEntity tileentity = blockstate.hasTileEntity() ? worldIn.getTileEntity(blockpos2) : null;
          spawnDrops(blockstate, worldIn, blockpos2, tileentity);
          worldIn.setBlockState(blockpos2, Blocks.AIR.getDefaultState(), 18);
          --k;
          ablockstate[k] = blockstate;
       }

       for(int l = list.size() - 1; l >= 0; --l) {
          BlockPos blockpos3 = list.get(l);
          BlockState blockstate3 = worldIn.getBlockState(blockpos3);
          blockpos3 = blockpos3.offset(direction);
          set.remove(blockpos3);
          worldIn.setBlockState(blockpos3, Blocks.MOVING_PISTON.getDefaultState().with(FACING, directionIn), 68);
          worldIn.setTileEntity(blockpos3, MovingPistonBlock.createTilePiston(list1.get(l), directionIn, extending, false));
          --k;
          ablockstate[k] = blockstate3;
       }

       if (extending) {
          PistonType pistontype = this.isSticky ? PistonType.STICKY : PistonType.DEFAULT;
          BlockState blockstate1 = Blocks.PISTON_HEAD.getDefaultState().with(PistonHeadBlock.FACING, directionIn).with(PistonHeadBlock.TYPE, pistontype);
          BlockState blockstate4 = Blocks.MOVING_PISTON.getDefaultState().with(MovingPistonBlock.FACING, directionIn).with(MovingPistonBlock.TYPE, this.isSticky ? PistonType.STICKY : PistonType.DEFAULT);
          set.remove(blockpos);
          worldIn.setBlockState(blockpos, blockstate4, 68);
          worldIn.setTileEntity(blockpos, MovingPistonBlock.createTilePiston(blockstate1, directionIn, true, true));
       }

       for(BlockPos blockpos4 : set) {
          worldIn.setBlockState(blockpos4, Blocks.AIR.getDefaultState(), 66);
       }

       for(int i1 = list2.size() - 1; i1 >= 0; --i1) {
          BlockState blockstate2 = ablockstate[k++];
          BlockPos blockpos5 = list2.get(i1);
          blockstate2.updateDiagonalNeighbors(worldIn, blockpos5, 2);
          worldIn.notifyNeighborsOfStateChange(blockpos5, blockstate2.getBlock());
       }

       for(int j1 = list.size() - 1; j1 >= 0; --j1) {
          worldIn.notifyNeighborsOfStateChange(list.get(j1), ablockstate[k++].getBlock());
       }

       if (extending) {
          worldIn.notifyNeighborsOfStateChange(blockpos, Blocks.PISTON_HEAD);
       }

       return true;
    }
 }

}
