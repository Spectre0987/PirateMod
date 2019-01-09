package net.pirates.mod.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * 
 */
public class ModelSash extends ModelBiped {
    public ModelRenderer bipedBody;
    public ModelRenderer bipedBody_1;
    public ModelRenderer bipedBody_2;
    public ModelRenderer bipedBody_3;

    public ModelSash() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.bipedBody = new ModelRenderer(this, 0, 0);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-5.0F, 8.0F, -3.0F, 1, 3, 6, 0.0F);
        this.bipedBody_1 = new ModelRenderer(this, 8, 0);
        this.bipedBody_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody_1.addBox(-4.0F, 8.0F, -3.0F, 9, 3, 1, 0.0F);
        this.bipedBody_3 = new ModelRenderer(this, 44, 0);
        this.bipedBody_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody_3.addBox(4.0F, 8.0F, -2.0F, 1, 9, 4, 0.0F);
        this.bipedBody_2 = new ModelRenderer(this, 28, 0);
        this.bipedBody_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody_2.addBox(-4.0F, 8.0F, 2.0F, 9, 3, 1, 0.0F);
        
        super.bipedBody.addChild(this.bipedBody);
        super.bipedBody.addChild(this.bipedBody_1);
        super.bipedBody.addChild(this.bipedBody_2);
        super.bipedBody.addChild(this.bipedBody_3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    	this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	GlStateManager.pushMatrix();
    	
    	if(entity != null && entity.isSneaking()) {
    		GlStateManager.translate(0, 0.2, 0);
    	}
        super.bipedBody.render(f5);
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
