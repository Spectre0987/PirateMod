package net.pirates.mod.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelPirateHat extends ModelBiped {
	private final ModelRenderer Animate;
	private final ModelRenderer tricorn;
	private final ModelRenderer core;
	private final ModelRenderer left_brim;
	private final ModelRenderer right_brim;
	private final ModelRenderer badge;

	public ModelPirateHat() {
		textureWidth = 64;
		textureHeight = 64;

		Animate = new ModelRenderer(this);
		Animate.setRotationPoint(0.0F, -1.0F, 0.0F);

		tricorn = new ModelRenderer(this);
		tricorn.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(tricorn, 0.0F, 1.5708F, 0.0F);
		Animate.addChild(tricorn);

		core = new ModelRenderer(this);
		core.setRotationPoint(0.0F, 25.0F, 0.0F);
		tricorn.addChild(core);
		core.cubeList.add(new ModelBox(core, 0, 0, -4.5F, -33.0F, -4.5F, 9, 3, 9, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 20, 12, 4.5F, -33.0F, -3.5F, 1, 3, 7, 0.0F, false));
		core.cubeList.add(new ModelBox(core, 0, 12, -2.5F, -33.75F, -2.5F, 5, 1, 5, 0.0F, false));

		left_brim = new ModelRenderer(this);
		left_brim.setRotationPoint(0.0F, -6.5F, 5.5F);
		setRotationAngle(left_brim, -0.1745F, 0.0873F, 0.0873F);
		tricorn.addChild(left_brim);
		left_brim.cubeList.add(new ModelBox(left_brim, 20, 22, -4.0F, -2.0F, -1.5F, 9, 3, 1, 0.0F, false));
		left_brim.cubeList.add(new ModelBox(left_brim, 24, 26, -5.0F, -2.0F, -3.5F, 1, 3, 3, 0.0F, false));
		left_brim.cubeList.add(new ModelBox(left_brim, 16, 26, 5.0F, -2.0F, -3.5F, 1, 3, 3, 0.0F, false));

		right_brim = new ModelRenderer(this);
		right_brim.setRotationPoint(0.0F, -6.5F, -4.5F);
		setRotationAngle(right_brim, 0.1745F, -0.0873F, 0.0873F);
		tricorn.addChild(right_brim);
		right_brim.cubeList.add(new ModelBox(right_brim, 0, 22, -4.0F, -2.25F, -0.5F, 9, 3, 1, 0.0F, false));
		right_brim.cubeList.add(new ModelBox(right_brim, 8, 26, -5.0F, -2.25F, -0.5F, 1, 3, 3, 0.0F, false));
		right_brim.cubeList.add(new ModelBox(right_brim, 0, 26, 5.0F, -2.25F, -0.5F, 1, 3, 3, 0.0F, false));

		badge = new ModelRenderer(this);
		badge.setRotationPoint(6.0F, -6.75F, 0.0F);
		setRotationAngle(badge, -0.7854F, 0.0F, 0.0F);
		tricorn.addChild(badge);
		badge.cubeList.add(new ModelBox(badge, 0, 32, -1.0F, -0.75F, -0.75F, 1, 2, 2, 0.0F, false));
		badge.cubeList.add(new ModelBox(badge, 0, 32, -1.25F, -1.25F, -0.25F, 1, 3, 1, 0.0F, false));
		badge.cubeList.add(new ModelBox(badge, 0, 32, -1.25F, -0.25F, -1.25F, 1, 1, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.pushMatrix();
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.Animate.rotateAngleX = this.bipedHead.rotateAngleX;
		this.Animate.rotateAngleY = this.bipedHead.rotateAngleY;
		this.Animate.rotateAngleZ = this.bipedHead.rotateAngleZ;
		if(entity != null && entity.isSneaking())
			GlStateManager.translate(0, 0.25, 0);
		Animate.render(f5);
		GlStateManager.popMatrix();
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}