package net.pirates.mod.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelPirateHat extends ModelBiped
{
  //fields
    ModelRenderer Brim1;
    ModelRenderer Brim4;
    ModelRenderer Brim2;
    ModelRenderer Brim5;
    ModelRenderer Brim3;
    ModelRenderer Brim6;
    ModelRenderer Brim7;
    ModelRenderer Hat;
  
  public ModelPirateHat()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Brim1 = new ModelRenderer(this, 0, 0);
      Brim1.addBox(-5F, -11F, -5.2F, 1, 4, 12);
      Brim1.setRotationPoint(0F, 0F, 0F);
      Brim1.setTextureSize(128, 128);
      Brim1.mirror = true;
      setRotation(Brim1, 0F, -0.3490659F, 0F);
      Brim4 = new ModelRenderer(this, 0, 0);
      Brim4.addBox(5F, -11F, -5F, 1, 4, 12);
      Brim4.setRotationPoint(0F, 0F, 0F);
      Brim4.setTextureSize(128, 128);
      Brim4.mirror = true;
      setRotation(Brim4, 0F, 0.3490659F, 0F);
      Brim2 = new ModelRenderer(this, 0, 11);
      Brim2.addBox(-4F, -8F, -5F, 3, 1, 10);
      Brim2.setRotationPoint(0F, 0F, 0F);
      Brim2.setTextureSize(128, 128);
      Brim2.mirror = true;
      setRotation(Brim2, 0F, -0.3490659F, 0F);
      Brim5 = new ModelRenderer(this, 0, 11);
      Brim5.addBox(2F, -8F, -5F, 3, 1, 10);
      Brim5.setRotationPoint(0F, 0F, 0F);
      Brim5.setTextureSize(128, 128);
      Brim5.mirror = true;
      setRotation(Brim5, 0F, 0.3490659F, 0F);
      Brim3 = new ModelRenderer(this, 0, 23);
      Brim3.addBox(-6F, -8F, 2.8F, 13, 1, 2);
      Brim3.setRotationPoint(0F, 0F, 0F);
      Brim3.setTextureSize(128, 128);
      Brim3.mirror = true;
      setRotation(Brim3, 0F, 0F, 0F);
      Brim6 = new ModelRenderer(this, 0, 28);
      Brim6.addBox(-2F, -8F, -6.3F, 6, 1, 10);
      Brim6.setRotationPoint(0F, 0F, 0F);
      Brim6.setTextureSize(128, 128);
      Brim6.mirror = true;
      setRotation(Brim6, 0F, 0F, 0F);
      Brim7 = new ModelRenderer(this, 0, 11);
      Brim7.addBox(2F, -8F, -5F, 3, 1, 10);
      Brim7.setRotationPoint(0F, 0F, 0F);
      Brim7.setTextureSize(128, 128);
      Brim7.mirror = true;
      setRotation(Brim7, 0F, 0.3490659F, 0F);
      Hat = new ModelRenderer(this, 0, 0);
      Hat.addBox(-2.5F, -12F, -3F, 6, 4, 6);
      Hat.setRotationPoint(0F, 0F, 0F);
      Hat.setTextureSize(128, 128);
      Hat.mirror = true;
      setRotation(Hat, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    GlStateManager.pushMatrix();
    GlStateManager.rotate(f3, 0, 1, 0);
    GlStateManager.rotate(f4, 1, 0, 0);
    Brim1.render(f5);
    Brim4.render(f5);
    Brim2.render(f5);
    Brim5.render(f5);
    Brim3.render(f5);
    Brim6.render(f5);
    Brim7.render(f5);
    Hat.render(f5);
    GlStateManager.popMatrix();
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
