package net.pirates.mod.client.models;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.pirates.mod.entity.EntityGhostPirate;

public class ModelPirate extends ModelPlayer{

	public ModelPirate() {
		super(0.0625F, false);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		if(entityIn instanceof EntityGhostPirate) {
			EntityGhostPirate pirate = (EntityGhostPirate)entityIn;
			if(pirate.isShooting()) {
				this.getMainArm(entityIn).rotateAngleX = this.bipedHead.rotateAngleX - (float)Math.toRadians(80);
				this.getMainArm(entityIn).rotateAngleY = this.bipedHead.rotateAngleY;
				this.getMainArm(entityIn).rotateAngleZ = this.bipedHead.rotateAngleZ;
			}
			//if(pirate.) {}
		}
		this.bipedBody.render(scale);
		this.bipedHead.render(scale);
		this.bipedLeftArm.render(scale);
		this.bipedLeftLeg.render(scale);
		this.bipedRightArm.render(scale);
		this.bipedRightLeg.render(scale);
	}
	
	private ModelRenderer getMainArm(Entity entity) {
		if(entity instanceof EntityLivingBase) {
			return ((EntityLivingBase)entity).getPrimaryHand() == EnumHandSide.RIGHT ? this.bipedRightArm : this.bipedLeftArm;
		}
		return this.bipedRightArm;
	}

}
