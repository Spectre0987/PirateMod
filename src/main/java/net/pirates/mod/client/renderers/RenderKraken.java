package net.pirates.mod.client.renderers;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.Pirate;
import net.pirates.mod.client.models.ModelKraken;
import net.pirates.mod.entity.EntityKraken;

public class RenderKraken extends RenderLiving<EntityKraken>{

	public static final ModelKraken model = new ModelKraken();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Pirate.MODID, "textures/entity/kraken.png");
	
	public RenderKraken(RenderManager rendermanagerIn) {
		super(rendermanagerIn, model, 5.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityKraken entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(EntityKraken entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

}
