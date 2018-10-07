package net.pirates.mod.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.pirates.mod.client.util.ModelUtil;

public class ModelPirateBoots extends ModelBiped
{
  //fields
    ModelRenderer LeftBoot;
    ModelRenderer LeftToe;
    ModelRenderer LeftBootCuff;
    ModelRenderer RightBoot;
    ModelRenderer RightToe;
    ModelRenderer RightBootCuff;
  
  public ModelPirateBoots()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      LeftBoot = new ModelRenderer(this, 0, 9);
      LeftBoot.addBox(-2F, 7F, -2F, 4, 5, 4);
      LeftBoot.setRotationPoint(2F, 12F, 0F);
      LeftBoot.setTextureSize(128, 128);
      LeftBoot.mirror = true;
      setRotation(LeftBoot, 0F, 0F, 0F);
      LeftToe = new ModelRenderer(this, 20, 0);
      LeftToe.addBox(-2F, 11F, 2F, 4, 1, 1);
      LeftToe.setRotationPoint(2F, 12F, 0F);
      LeftToe.setTextureSize(128, 128);
      LeftToe.mirror = true;
      setRotation(LeftToe, 0F, 0F, 0F);
      LeftBootCuff = new ModelRenderer(this, 0, 0);
      LeftBootCuff.addBox(-2.5F, 3F, -2.5F, 5, 4, 5);
      LeftBootCuff.setRotationPoint(2F, 12F, 0F);
      LeftBootCuff.setTextureSize(128, 128);
      LeftBootCuff.mirror = true;
      setRotation(LeftBootCuff, 0F, 0F, 0F);
      RightBoot = new ModelRenderer(this, 0, 9);
      RightBoot.addBox(-2F, 7F, -2F, 4, 5, 4);
      RightBoot.setRotationPoint(-2F, 12F, 0F);
      RightBoot.setTextureSize(128, 128);
      RightBoot.mirror = true;
      setRotation(RightBoot, 0F, 0F, 0F);
      RightToe = new ModelRenderer(this, 20, 0);
      RightToe.addBox(-2F, 11F, 2F, 4, 1, 1);
      RightToe.setRotationPoint(-2F, 12F, 0F);
      RightToe.setTextureSize(128, 128);
      RightToe.mirror = true;
      setRotation(RightToe, 0F, 0F, 0F);
      RightBootCuff = new ModelRenderer(this, 0, 0);
      RightBootCuff.addBox(-2.5F, 3F, -2.5F, 5, 4, 5);
      RightBootCuff.setRotationPoint(-2F, 12F, 0F);
      RightBootCuff.setTextureSize(128, 128);
      RightBootCuff.mirror = true;
      setRotation(RightBootCuff, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	GlStateManager.pushMatrix();
	GlStateManager.rotate(180,0,1,0);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    ModelUtil.copyAngles(this.bipedLeftLeg, this.LeftBoot);
    ModelUtil.copyAngles(this.bipedLeftLeg, this.LeftBootCuff);
    ModelUtil.copyAngles(this.bipedLeftLeg, this.LeftToe);
    ModelUtil.copyAngles(this.bipedRightLeg, this.RightBoot);
    ModelUtil.copyAngles(this.bipedRightLeg, this.RightBootCuff);
    ModelUtil.copyAngles(this.bipedRightLeg, this.RightToe);
    LeftBoot.render(f5);
    LeftToe.render(f5);
    LeftBootCuff.render(f5);
    RightBoot.render(f5);
    RightToe.render(f5);
    RightBootCuff.render(f5);
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
