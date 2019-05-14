package net.pirates.mod.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelBarbossaHat extends ModelBiped {
	private final ModelRenderer hat;
	private final ModelRenderer interior;

	public ModelBarbossaHat() {
		textureWidth = 128;
		textureHeight = 128;

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, 0.0F, 0.0F);

		interior = new ModelRenderer(this);
		interior.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(interior, 0.0F, 0.0F, 0.1745F);
		hat.addChild(interior);
		interior.cubeList.add(new ModelBox(interior, 0, 19, -5.75F, -10.0F, -4.5F, 9, 4, 9, 0.0F, false));
		interior.cubeList.add(new ModelBox(interior, 0, 0, -9.75F, -6.0F, -9.5F, 18, 1, 18, 0.0F, false));
		interior.cubeList.add(new ModelBox(interior, 36, 19, 3.3F, -16.0F, -4.5F, 0, 10, 9, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		GlStateManager.pushMatrix();
		if(entity instanceof EntityLivingBase && ((EntityLivingBase)entity).isSneaking())
			GlStateManager.translate(0, 0.2, 0);
		hat.rotateAngleX = this.bipedHead.rotateAngleX;
		hat.rotateAngleY = this.bipedHead.rotateAngleY;
		hat.rotateAngleZ = this.bipedHead.rotateAngleZ;
		hat.render(f5);
		GlStateManager.popMatrix();
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}