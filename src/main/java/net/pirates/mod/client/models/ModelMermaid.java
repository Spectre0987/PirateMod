package net.pirates.mod.client.models;

import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelMermaid extends ModelBase {
	
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedHead;
    public ModelRenderer tail_top;
    public ModelRenderer tail_mid;
    public ModelRenderer tail_bottom;
    public ModelRenderer tail_spike;
    public ModelRenderer tail_spike_1;

    public ModelMermaid() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.setRotateAngle(bipedBody, 1.5707963705062866F, 0.0F, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.setRotateAngle(bipedLeftArm, 0.0F, 0.0F, -0.10000736613927509F);
        this.tail_spike_1 = new ModelRenderer(this, 40, 45);
        this.tail_spike_1.mirror = true;
        this.tail_spike_1.setRotationPoint(0.0F, 9.0F, -1.4F);
        this.tail_spike_1.addBox(-6.2F, -0.3F, 0.2F, 12, 9, 0, 0.0F);
        this.setRotateAngle(tail_spike_1, 0.015533430342749534F, 0.0F, 0.0F);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(bipedHead, -1.2566370614359172F, 0.0F, 0.0F);
        this.tail_mid = new ModelRenderer(this, 0, 44);
        this.tail_mid.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.tail_mid.addBox(-4.0F, 0.0F, -2.0F, 8, 8, 3, 0.0F);
        this.setRotateAngle(tail_mid, 0.00767944870877505F, 0.0F, 0.0F);
        this.tail_bottom = new ModelRenderer(this, 24, 32);
        this.tail_bottom.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.tail_bottom.addBox(-4.0F, 0.0F, -2.0F, 8, 8, 2, 0.0F);
        this.setRotateAngle(tail_bottom, 0.00767944870877505F, 0.0F, 0.0F);
        this.tail_top = new ModelRenderer(this, 0, 32);
        this.tail_top.setRotationPoint(0.0F, 12.0F, 0.1F);
        this.tail_top.addBox(-4.0F, 0.0F, -2.0F, 8, 8, 4, 0.0F);
        this.setRotateAngle(tail_top, 0.015533430342749534F, 0.0F, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);
        this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.setRotateAngle(bipedRightArm, 0.0F, 0.0F, 0.10000736613927509F);
        this.tail_spike = new ModelRenderer(this, 45, 32);
        this.tail_spike.mirror = true;
        this.tail_spike.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.tail_spike.addBox(-1.3F, -0.3F, -2.0F, 3, 8, 2, 0.0F);
        this.setRotateAngle(tail_spike, 0.015533430342749534F, 0.0F, 0.0F);
        this.bipedBody.addChild(this.bipedLeftArm);
        this.tail_bottom.addChild(this.tail_spike_1);
        this.bipedBody.addChild(this.bipedHead);
        this.tail_top.addChild(this.tail_mid);
        this.tail_mid.addChild(this.tail_bottom);
        this.bipedBody.addChild(this.tail_top);
        this.bipedBody.addChild(this.bipedRightArm);
        this.tail_bottom.addChild(this.tail_spike);
    }

    @Override
    public void render(Entity entity, float f, float f1, float time, float headYaw, float headPitch, float f5) {
    	GlStateManager.pushMatrix();
    	if(entity == null)return;
    	if(entity.world.getBlockState(entity.getPosition().up()).getMaterial() == Material.WATER) {
    		GlStateManager.translate(0, 1, 0);
    		GlStateManager.rotate(entity.rotationPitch, 1, 0, 0);
    		this.bipedBody.rotateAngleX = (float) Math.toRadians(90);
    		float angle = (float)Math.toRadians(Math.cos(time * 0.1) * 10);
    		this.tail_top.rotateAngleX = angle;
    		this.tail_mid.rotateAngleX = angle / 2;
    		this.tail_spike.rotateAngleX = angle;
    		this.tail_bottom.rotateAngleX = angle / 2;
    		this.tail_spike_1.rotateAngleX = this.tail_spike.rotateAngleX;
    		
    		this.bipedHead.rotateAngleX = -(float)Math.toRadians(85);
    		
    		this.bipedLeftArm.rotateAngleZ = (float)Math.toRadians(Math.cos(time * 0.1) - 3);
    		this.bipedRightArm.rotateAngleZ = (float)Math.toRadians(Math.sin(time * 0.1) + 3);
    	}
    	else{
    		this.bipedBody.rotateAngleX = (float) Math.toRadians(0);
    		this.tail_top.rotateAngleX = (float)Math.toRadians(20);
    		this.tail_mid.rotateAngleX = (float)Math.toRadians(70);
    		
    		//this.bipedLeftArm.rotateAngleZ = (float)Math.toRadians((Math.cos(time * 0.1) * 2) - 5);
    		//this.bipedRightArm.rotateAngleZ = (float)Math.toRadians((Math.sin(time * 0.1) * 2) + 5);
    		this.bipedHead.rotateAngleX = 0;
    	}
    	this.bipedHead.rotateAngleY = 0;
        this.bipedBody.render(f5);
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
