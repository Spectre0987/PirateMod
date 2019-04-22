package net.pirates.mod.client.renderers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.entity.EntityWater;

public class RenderWater extends Render<EntityWater>{

	public RenderWater(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWater entity) {
		return null;
	}

	@Override
	public void doRender(EntityWater entity, double x, double y, double z, float entityYaw, float partialTicks) {
		entity.world.spawnParticle(EnumParticleTypes.WATER_SPLASH, entity.posX, entity.posY, entity.posZ, 0, 0, 0);
	}
}
