package net.pirates.mod.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShark extends ModelBase
{
  //fields
    ModelRenderer TailFin;
    ModelRenderer Body;
    ModelRenderer MidSection;
    ModelRenderer Tail;
    ModelRenderer SmallTail;
    ModelRenderer Head;
    ModelRenderer FinRight;
    ModelRenderer FinLeft;
    ModelRenderer TopFin;
  
  public ModelShark()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      TailFin = new ModelRenderer(this, 0, 43);
      TailFin.addBox(0F, -5F, 0F, 0, 10, 5);
      TailFin.setRotationPoint(0F, 20F, 20F);
      TailFin.setTextureSize(128, 128);
      TailFin.mirror = true;
      setRotation(TailFin, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 0, 11);
      Body.addBox(-3F, -0.5F, -4F, 6, 1, 4);
      Body.setRotationPoint(0F, 21.5F, -7F);
      Body.setTextureSize(128, 128);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      MidSection = new ModelRenderer(this, 0, 16);
      MidSection.addBox(-3F, -3F, 0F, 6, 6, 13);
      MidSection.setRotationPoint(0F, 20F, 3F);
      MidSection.setTextureSize(128, 128);
      MidSection.mirror = true;
      setRotation(MidSection, 0F, 0F, 0F);
      Tail = new ModelRenderer(this, 0, 35);
      Tail.addBox(-1F, -2F, 0F, 2, 4, 4);
      Tail.setRotationPoint(0F, 0F, 16F);
      Tail.setTextureSize(128, 128);
      Tail.mirror = true;
      setRotation(Tail, 0F, 0F, 0F);
      SmallTail = new ModelRenderer(this, 24, 0);
      SmallTail.addBox(0F, 0F, 0F, 8, 8, 10);
      SmallTail.setRotationPoint(-4F, 16F, -7F);
      SmallTail.setTextureSize(128, 128);
      SmallTail.mirror = true;
      setRotation(SmallTail, 0F, 0F, 0F);
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(-3F, -3F, -6F, 6, 4, 6);
      Head.setRotationPoint(0F, 20F, -7F);
      Head.setTextureSize(128, 128);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      FinRight = new ModelRenderer(this, 38, 22);
      FinRight.addBox(0F, -0.5F, 0F, 6, 1, 3);
      FinRight.setRotationPoint(-4F, 21F, -4F);
      FinRight.setTextureSize(128, 128);
      FinRight.mirror = true;
      setRotation(FinRight, 0F, 0F, 2.83616F);
      FinLeft = new ModelRenderer(this, 37, 18);
      FinLeft.addBox(0F, -0.5F, 0F, 6, 1, 3);
      FinLeft.setRotationPoint(4F, 21F, -4F);
      FinLeft.setTextureSize(128, 128);
      FinLeft.mirror = true;
      setRotation(FinLeft, 0F, 0F, 0.3926991F);
      TopFin = new ModelRenderer(this, 60, 0);
      TopFin.addBox(0F, -8F, 0F, 0, 8, 5);
      TopFin.setRotationPoint(0F, 16F, -5F);
      TopFin.setTextureSize(128, 128);
      TopFin.mirror = true;
      setRotation(TopFin, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
	
    Body.render(f5);
    MidSection.render(f5);
    SmallTail.render(f5);
    this.Tail.render(f5);
    this.TailFin.render(f5);
    
    Head.render(f5);
    FinRight.render(f5);
    FinLeft.render(f5);
    TopFin.render(f5);
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
