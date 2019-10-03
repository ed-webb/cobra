package com.funbluebits.cobra.client.renders;

import com.funbluebits.cobra.entities.SnakeEntity;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class CobraRenderRegistry {

  public static void registryEntityRenders() {
    RenderingRegistry.registerEntityRenderingHandler(SnakeEntity.class, new SnakeRenderer.RenderFactory());
  }
}
