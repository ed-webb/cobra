package com.funbluebits.cobra.entities;

import com.funbluebits.cobra.init.ModEntities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class SnakeEntity extends AnimalEntity {

  @SuppressWarnings("unchecked")
  public SnakeEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
    super( (EntityType<? extends AnimalEntity>) ModEntities.GREEN_SNAKE, worldIn);
    this.experienceValue = 50;  // FIXME not sure we are getting all the expected XPs from this high a value ...
  }

  // Can add many other goals and attributes
  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(0, new SwimGoal(this));  // priority (lowest first), task
    this.goalSelector.addGoal(2, new BreedGoal(this, 0.75D));
    this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
    this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 0.5d)); // speed
    this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    //this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 0.5d, false));
  }
  
  @Override
  protected void registerAttributes() {
    super.registerAttributes();
    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0d);
    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5d);
    //this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0d);  // test this value TODO
  }


  public SnakeEntity createChild(AgeableEntity ageable) {
     return (SnakeEntity) ModEntities.GREEN_SNAKE.create(this.world);
  }

  /**
   * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
   * the animal type)
   */
  public boolean isBreedingItem(ItemStack stack) {
     return stack.getItem() == Items.WHEAT;
  }

  protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
     return this.isChild() ? sizeIn.height * 0.95F : sizeIn.height * 0.95F;  // TODO 0.95 and 1.3 are values for cow.
  }
}
