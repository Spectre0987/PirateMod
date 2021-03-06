package net.pirates.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.client.ModelRegistry;
import net.pirates.mod.entity.EntityDingy;
import net.pirates.mod.items.PItems;

public class RenderDingy extends Render<EntityDingy>{

	public static final ItemStack boat_stack = new ItemStack(PItems.dingy);
	
	public RenderDingy(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDingy entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doRender(EntityDingy entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 0.9, z);
		float yaw = -entity.rotationYaw - 180;
		float pYaw = -entity.prevRotationYaw - 180;
		GlStateManager.rotate(pYaw - (pYaw - yaw) * partialTicks, 0, 1, 0);
		GlStateManager.rotate((float)Math.cos(0.1 * entity.ticksExisted), 0, 0, 1);
		GlStateManager.scale(10 / 3, 10 / 3, 10 / 3);
		Minecraft.getMinecraft().getRenderItem().renderItem(boat_stack, TransformType.NONE);
		GlStateManager.popMatrix();
	}

}
