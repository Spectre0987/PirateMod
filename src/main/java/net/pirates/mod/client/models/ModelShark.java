package net.pirates.mod.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.pirates.mod.entity.EntityShark;

/**
 * EntityShark - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelShark extends ModelBase {
	
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer tail;

    public ModelShark() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.tail = new ModelRenderer(this, 0, 0);
        this.tail.setRotationPoint(0.0F, 0.0F, 22.0F);
        this.tail.addBox(0.0F, 0.0F, 0.0F, 4, 4, 22, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, -0.3F, 0.0F);
        this.body.addBox(0.0F, 0.2F, 0.0F, 4, 4, 22, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(-2.0F, 20.0F, -8.0F);
        this.head.addBox(-1.0F, -1.0F, -5.0F, 6, 6, 5, 0.0F);
        this.body.addChild(this.tail);
        this.head.addChild(this.body);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    	this.body.rotateAngleY = (float)Math.toRadians(45);
    	this.tail.rotateAngleY = (float)Math.toRadians(-25);
        this.head.render(f5);
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
