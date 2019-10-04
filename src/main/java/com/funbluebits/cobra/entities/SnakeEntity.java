package com.funbluebits.cobra.entities;

import com.funbluebits.cobra.RegisterEntities;
import com.funbluebits.cobra.init.ModEntities;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.world.World;

public class SnakeEntity extends CreatureEntity {

  @SuppressWarnings("unchecked")
  public SnakeEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
    super( (EntityType<? extends CreatureEntity>) ModEntities.green_snake, worldIn);
  }

  // Can add many other goals and attributes
  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(0, new SwimGoal(this));  // priority (lowest first), task
    this.goalSelector.addGoal(0, new RandomWalkingGoal(this, 0.5d)); // speed
    this.goalSelector.addGoal(0, new LookRandomlyGoal(this));
    //this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 0.5d, false));
  }
  
  @Override
  protected void registerAttributes() {
    super.registerAttributes();
    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0d);
    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5d);
    //this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0d);  // test this value TODO
  }
}
