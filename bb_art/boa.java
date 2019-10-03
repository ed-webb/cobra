//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class boa extends ModelBase {
	private final ModelRenderer head;
	private final ModelRenderer t1;
	private final ModelRenderer t2;
	private final ModelRenderer t3;
	private final ModelRenderer t4;
	private final ModelRenderer t5;

	public boa() {
		textureWidth = 32;
		textureHeight = 32;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -8.0F, 4, 4, 4, 0.0F, false));

		t1 = new ModelRenderer(this);
		t1.setRotationPoint(0.0F, 24.0F, 0.0F);
		t1.cubeList.add(new ModelBox(t1, 0, 0, -3.0F, -4.0F, -5.0F, 2, 2, 2, 0.0F, false));

		t2 = new ModelRenderer(this);
		t2.setRotationPoint(0.0F, 24.0F, 0.0F);
		t2.cubeList.add(new ModelBox(t2, 0, 0, -3.0F, -2.0F, -4.0F, 2, 2, 2, 0.0F, false));

		t3 = new ModelRenderer(this);
		t3.setRotationPoint(0.0F, 24.0F, 0.0F);
		t3.cubeList.add(new ModelBox(t3, 0, 0, -2.0F, -3.0F, -2.0F, 2, 2, 2, 0.0F, false));

		t4 = new ModelRenderer(this);
		t4.setRotationPoint(0.0F, 24.0F, 0.0F);
		t4.cubeList.add(new ModelBox(t4, 0, 0, -3.0F, -2.0F, 0.0F, 2, 2, 2, 0.0F, false));

		t5 = new ModelRenderer(this);
		t5.setRotationPoint(0.0F, 24.0F, 0.0F);
		t5.cubeList.add(new ModelBox(t5, 0, 0, -2.0F, -2.0F, 2.0F, 2, 2, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		t1.render(f5);
		t2.render(f5);
		t3.render(f5);
		t4.render(f5);
		t5.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}