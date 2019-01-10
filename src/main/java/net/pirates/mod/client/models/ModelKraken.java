package net.pirates.mod.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelOctopus - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelKraken extends ModelBase {
	
    public ModelRenderer body;
    public ModelRenderer top_0;
    public ModelRenderer top_1;
    public ModelRenderer top_2;
    public ModelRenderer top_3;
    public ModelRenderer top_4;
    public ModelRenderer top_5;
    public ModelRenderer top_6;
    public ModelRenderer top_7;
    public ModelRenderer bottom_0;
    public ModelRenderer bottom_1;
    public ModelRenderer bottom_2;
    public ModelRenderer bottom_3;
    public ModelRenderer bottom_4;
    public ModelRenderer bottom_5;
    public ModelRenderer bottom_6;
    public ModelRenderer bottom_7;

    public ModelKraken() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.bottom_3 = new ModelRenderer(this, 10, 50);
        this.bottom_3.setRotationPoint(1.0F, -32.0F, 1.0F);
        this.bottom_3.addBox(-1.0F, -32.0F, -1.0F, 2, 32, 2, 0.0F);
        this.bottom_2 = new ModelRenderer(this, 10, 50);
        this.bottom_2.setRotationPoint(1.0F, -32.0F, 1.0F);
        this.bottom_2.addBox(-1.0F, -33.0F, -1.0F, 2, 32, 2, 0.0F);
        this.bottom_1 = new ModelRenderer(this, 10, 50);
        this.bottom_1.setRotationPoint(0.5F, -32.0F, 1.0F);
        this.bottom_1.addBox(-0.5F, -33.0F, -1.0F, 2, 32, 2, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(-8.0F, -8.0F, -8.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 16, 32, 16, 0.0F);
        this.bottom_5 = new ModelRenderer(this, 10, 50);
        this.bottom_5.setRotationPoint(1.0F, -32.0F, 1.0F);
        this.bottom_5.addBox(-1.0F, -33.0F, -1.0F, 2, 32, 2, 0.0F);
        this.bottom_6 = new ModelRenderer(this, 10, 50);
        this.bottom_6.setRotationPoint(1.0F, -32.0F, 1.0F);
        this.bottom_6.addBox(-1.0F, -32.0F, -1.0F, 2, 32, 2, 0.0F);
        this.top_4 = new ModelRenderer(this, 0, 50);
        this.top_4.setRotationPoint(0.0F, 0.0F, 14.0F);
        this.top_4.addBox(0.0F, -32.0F, 0.0F, 2, 32, 2, 0.0F);
        this.top_2 = new ModelRenderer(this, 0, 50);
        this.top_2.setRotationPoint(14.0F, 1.0F, 0.0F);
        this.top_2.addBox(0.0F, -33.0F, 0.0F, 2, 32, 2, 0.0F);
        this.top_7 = new ModelRenderer(this, 0, 50);
        this.top_7.setRotationPoint(7.0F, 1.0F, 14.0F);
        this.top_7.addBox(0.0F, -33.0F, 0.0F, 2, 32, 2, 0.0F);
        this.bottom_7 = new ModelRenderer(this, 10, 50);
        this.bottom_7.setRotationPoint(1.0F, -32.0F, 1.0F);
        this.bottom_7.addBox(-1.0F, -33.0F, -1.0F, 2, 32, 2, 0.0F);
        this.top_6 = new ModelRenderer(this, 0, 50);
        this.top_6.setRotationPoint(14.0F, 1.0F, 14.0F);
        this.top_6.addBox(0.0F, -33.0F, 0.0F, 2, 32, 2, 0.0F);
        this.top_1 = new ModelRenderer(this, 0, 50);
        this.top_1.setRotationPoint(7.0F, 1.0F, 0.0F);
        this.top_1.addBox(0.0F, -33.0F, 0.0F, 2, 32, 2, 0.0F);
        this.top_5 = new ModelRenderer(this, 0, 50);
        this.top_5.setRotationPoint(14.0F, 1.0F, 7.0F);
        this.top_5.addBox(0.0F, -33.0F, 0.0F, 2, 32, 2, 0.0F);
        this.bottom_0 = new ModelRenderer(this, 10, 50);
        this.bottom_0.setRotationPoint(0.5F, -32.0F, 1.0F);
        this.bottom_0.addBox(-0.5F, -32.0F, -1.0F, 2, 32, 2, 0.0F);
        this.bottom_4 = new ModelRenderer(this, 10, 50);
        this.bottom_4.setRotationPoint(1.0F, -32.0F, 1.0F);
        this.bottom_4.addBox(-1.0F, -32.0F, -1.0F, 2, 32, 2, 0.0F);
        this.top_0 = new ModelRenderer(this, 0, 50);
        this.top_0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.top_0.addBox(0.0F, -32.0F, 0.0F, 2, 32, 2, 0.0F);
        this.top_3 = new ModelRenderer(this, 0, 50);
        this.top_3.setRotationPoint(0.0F, 0.0F, 7.0F);
        this.top_3.addBox(0.0F, -32.0F, 0.0F, 2, 32, 2, 0.0F);
        this.top_3.addChild(this.bottom_3);
        this.top_2.addChild(this.bottom_2);
        this.top_1.addChild(this.bottom_1);
        this.top_5.addChild(this.bottom_5);
        this.top_6.addChild(this.bottom_6);
        this.body.addChild(this.top_4);
        this.body.addChild(this.top_2);
        this.body.addChild(this.top_7);
        this.top_7.addChild(this.bottom_7);
        this.body.addChild(this.top_6);
        this.body.addChild(this.top_1);
        this.body.addChild(this.top_5);
        this.top_0.addChild(this.bottom_0);
        this.top_4.addChild(this.bottom_4);
        this.body.addChild(this.top_0);
        this.body.addChild(this.top_3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
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
