package net.pirates.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.client.models.ModelPirate;
import net.pirates.mod.entity.EntityGhostPirate;

public class RenderGhostPirate extends RenderLiving<EntityGhostPirate>{

	public static ModelPirate model = new ModelPirate();
	
	public RenderGhostPirate(RenderManager rendermanagerIn) {
		super(rendermanagerIn, model, 0F);
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerBipedArmor(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGhostPirate entity) {
		ResourceLocation skin = entity.getRank().getSkin(entity.getSkinIndex());
		return skin;
	}

	@Override
	public void doRender(EntityGhostPirate entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if(entity.world.getWorldTime() > 13000 && entity.world.getMoonPhase() != 4) {
			GlStateManager.pushMatrix();
			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();
			GlStateManager.color(0.384F, 0.737F, 0.607F, 0.5F);
			Minecraft.getMinecraft().entityRenderer.disableLightmap();
			super.doRender(entity, x, y, z, entityYaw, partialTicks);
			Minecraft.getMinecraft().entityRenderer.enableLightmap();
			GlStateManager.color(1, 1, 1, 1);
			GlStateManager.disableAlpha();
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}
	}

}
