package net.pirates.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.client.models.ModelShark;
import net.pirates.mod.entity.EntityShark;

public class RenderShark extends Render<EntityShark>{

	public RenderShark(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityShark entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(0, 0, 0, 0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("blocks/cactus.png"));
		new ModelShark().render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityShark entity) {
		return TextureMap.LOCATION_MISSING_TEXTURE;
	}

}
