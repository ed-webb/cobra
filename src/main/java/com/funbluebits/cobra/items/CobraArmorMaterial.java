package com.funbluebits.cobra.items;

import java.util.function.Supplier;

import com.funbluebits.cobra.CobraMod;
import com.funbluebits.cobra.init.ModItems;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum CobraArmorMaterial implements IArmorMaterial {
  SNAKE(CobraMod.MOD_ID + ":snake", 25, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 0.0F, () -> {
    return Ingredient.fromItems(ModItems.SNAKE_SCALE);
  });

  private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
  private final String name;
  private final int maxDamageFactor;
  private final int[] damageReductionAmountArray;
  private final int enchantability;
  private final SoundEvent soundEvent;
  private final float toughness;
  private final LazyLoadBase<Ingredient> repairMaterial;

  private CobraArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountsIn, int enchantabilityIn, SoundEvent equipSoundIn, float toughnessIn, Supplier<Ingredient> repairMaterialSupplier) {
    this.name = nameIn;
    this.maxDamageFactor = maxDamageFactorIn;
    this.damageReductionAmountArray = damageReductionAmountsIn;
    this.enchantability = enchantabilityIn;
    this.soundEvent = equipSoundIn;
    this.toughness = toughnessIn;
    this.repairMaterial = new LazyLoadBase<>(repairMaterialSupplier);
  }

  public int getDurability(EquipmentSlotType slotIn) {
    return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
  }

  public int getDamageReductionAmount(EquipmentSlotType slotIn) {
    return this.damageReductionAmountArray[slotIn.getIndex()];
  }

  public int getEnchantability() {
    return this.enchantability;
  }

  public SoundEvent getSoundEvent() {
    return this.soundEvent;
  }

  public Ingredient getRepairMaterial() {
    return this.repairMaterial.getValue();
  }

  @OnlyIn(Dist.CLIENT)
  public String getName() {
    return this.name;
  }

  public float getToughness() {
    return this.toughness;
  }
}
