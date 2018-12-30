package net.pirates.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.blocks.PBlocks;
import net.pirates.mod.entity.EntityCannonball;

public class RenderCannonball extends Render<EntityCannonball>{

	public RenderCannonball(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityCannonball entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(PBlocks.cannonball), TransformType.NONE);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCannonball entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

}
