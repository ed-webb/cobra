package com.funbluebits.cobra.client.models;

/**
 * After exporting the java from BlockBench - https://blockbench.net/
 * Do these conversions:
 * 1. For 1.14.4 ModelRenderer is replaced by RendererModel (case sensitive or check that method setRotationAngle() has not been messed up).
 * 2. ModelBase is replaced by EntityModel<SnakeEntity> Use your specific entity class here
 * 3. Add the imports as below.
 * 4. Use the @OnlyIn(Dist.CLIENT) annotation.
 */
import com.funbluebits.cobra.entities.SnakeEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnakeModel extends EntityModel<SnakeEntity> {
  private final RendererModel head;
  private final RendererModel t1;
  private final RendererModel t2;
  private final RendererModel t3;
  private final RendererModel t4;
  private final RendererModel t5;

  public SnakeModel() {
    textureWidth = 32;
    textureHeight = 32;

    head = new RendererModel(this);
    head.setRotationPoint(0.0F, 24.0F, 0.0F);
    head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -8.0F, 4, 4, 4, 0.0F, false));

    t1 = new RendererModel(this);
    t1.setRotationPoint(0.0F, 24.0F, 0.0F);
    t1.cubeList.add(new ModelBox(t1, 0, 0, -3.0F, -4.0F, -5.0F, 2, 2, 2, 0.0F, false));

    t2 = new RendererModel(this);
    t2.setRotationPoint(0.0F, 24.0F, 0.0F);
    t2.cubeList.add(new ModelBox(t2, 0, 0, -3.0F, -2.0F, -4.0F, 2, 2, 2, 0.0F, false));

    t3 = new RendererModel(this);
    t3.setRotationPoint(0.0F, 24.0F, 0.0F);
    t3.cubeList.add(new ModelBox(t3, 0, 0, -2.0F, -3.0F, -2.0F, 2, 2, 2, 0.0F, false));

    t4 = new RendererModel(this);
    t4.setRotationPoint(0.0F, 24.0F, 0.0F);
    t4.cubeList.add(new ModelBox(t4, 0, 0, -3.0F, -2.0F, 0.0F, 2, 2, 2, 0.0F, false));

    t5 = new RendererModel(this);
    t5.setRotationPoint(0.0F, 24.0F, 0.0F);
    t5.cubeList.add(new ModelBox(t5, 0, 0, -2.0F, -2.0F, 2.0F, 2, 2, 2, 0.0F, false));
  }

  @Override
  public void render(SnakeEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    head.render(f5);
    t1.render(f5);
    t2.render(f5);
    t3.render(f5);
    t4.render(f5);
    t5.render(f5);
  }
  public void setRotationAngle(RendererModel rendererModel, float x, float y, float z) {
    rendererModel.rotateAngleX = x;
    rendererModel.rotateAngleY = y;
    rendererModel.rotateAngleZ = z;
  }

}
