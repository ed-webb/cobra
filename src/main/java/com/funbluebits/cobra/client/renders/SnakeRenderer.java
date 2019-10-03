package com.funbluebits.cobra.client.renders;

import com.funbluebits.cobra.CobraMod;
import com.funbluebits.cobra.client.models.SnakeModel;
import com.funbluebits.cobra.entities.SnakeEntity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class SnakeRenderer extends LivingRenderer<SnakeEntity, SnakeModel> {

  public SnakeRenderer(EntityRendererManager manager) {
    super(manager, new SnakeModel(), 0f);
  }

  @Override
  protected ResourceLocation getEntityTexture(SnakeEntity entity) {
    return new ResourceLocation(CobraMod.MOD_ID, "textures/entity/green_snake.png");
  }
  
  public static class RenderFactory implements IRenderFactory<SnakeEntity> {

    @Override
    public EntityRenderer<? super SnakeEntity> createRenderFor(EntityRendererManager manager) {
      return new SnakeRenderer(manager);
    }
    
  }
}
