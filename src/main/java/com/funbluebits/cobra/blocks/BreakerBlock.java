package com.funbluebits.cobra.blocks;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.funbluebits.cobra.CobraMod;
import com.funbluebits.cobra.entities.BreakerTileEntity;

// inventory and GUI - see https://www.youtube.com/watch?v=K96bq34Semc&list=PLmaTwVFUUXiBE7m5d_uLIRyujqsPTYX4C&index=5

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.BarrelTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.minecraftforge.common.extensions.IForgeBlockState;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CobraMod.MOD_ID)
public class BreakerBlock extends HorizontalBlock implements IForgeBlock, ITileEntityProvider {

  protected static final Logger LOGGER = LogManager.getLogger();
  protected BreakerTileEntity tileEntity;
  
  public BreakerBlock(Block.Properties builder) {
    super(builder);
    LOGGER.info("BreakerBlock constructor");
  }

  public TileEntity createTileEntity(World world, IForgeBlockState state) {
    // TODO Auto-generated method stub
    LOGGER.info("BreakerBlock createTileEntity");
    this.tileEntity = new BreakerTileEntity();
    return tileEntity;
  }
  
  @Deprecated //Forge: New State sensitive version.
  public boolean hasTileEntity() {
    LOGGER.info("BreakerBlock hasTileEntity");
     return true;
  }

  @Override
  public TileEntity createNewTileEntity(IBlockReader worldIn) {
    // TODO Auto-generated method stub
    LOGGER.info("BreakerBlock createNewTileEntity");
    return new BreakerTileEntity();
  }

//  public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
//    LOGGER.info("BreakerBlock tick");
//    System.out.println(worldIn.getBlockState(pos).get(BlockStateProperties.HORIZONTAL_FACING));
//    worldIn.setBlockState(pos, state.cycle(BlockStateProperties.HORIZONTAL_FACING));
//    System.out.println(worldIn.getBlockState(pos).get(BlockStateProperties.HORIZONTAL_FACING));
////    TileEntity tileentity = worldIn.getTileEntity(pos);
////    if (tileentity instanceof BreakerTileEntity) {
////       ((BreakerTileEntity)tileentity).func_213962_h();
////    }
//
// }

  @SubscribeEvent
  public void onWorldTick(WorldTickEvent event)
  { 
    LOGGER.info("World tick");
//    event.world.
//    event.world. getBlockState(this.bl)
  }

  public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
    TileEntity tileentity = worldIn.getTileEntity(pos);
    LOGGER.info("BreakerBlock.tick() tileEntity = " + tileEntity + ", pos=" + pos);
    if (tileentity instanceof BreakerTileEntity) {
       ((BreakerTileEntity)tileentity).myTick();
    }

 }

}
