package net.pirates.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.Pirate;
import net.pirates.mod.client.models.ModelShark;
import net.pirates.mod.entity.EntityShark;

public class RenderShark extends Render<EntityShark>{

	Minecraft mc;
	ModelShark model = new ModelShark();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Pirate.MODID, "textures/entity/shark.png");
	
	public RenderShark(RenderManager renderManager) {
		super(renderManager);
		mc = Minecraft.getMinecraft();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityShark entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(EntityShark entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		GlStateManager.rotate(180,1,0,0);
		GlStateManager.rotate(entity.rotationYaw / 2, 0, 1, 0);
		GlStateManager.rotate(entity.rotationPitch, 1, 0, 0);
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(entity, entity.limbSwing, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
