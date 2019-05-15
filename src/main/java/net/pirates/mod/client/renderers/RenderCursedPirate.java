package net.pirates.mod.client.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.client.models.ModelCursedPirateSkele;
import net.pirates.mod.entity.pirates.EntityPirateCursed;

public class RenderCursedPirate extends RenderBiped<EntityPirateCursed>{

	public static ModelCursedPirateSkele SKELETON_MODEL = new ModelCursedPirateSkele();
	public static final ResourceLocation SKELETON = new ResourceLocation("minecraft:textures/entity/skeleton/skeleton.png");
	public static ModelPlayer STEVE = new ModelPlayer(0.0625F, false);
	public static ModelPlayer ALEX = new ModelPlayer(0.0312F, true);
	
	public RenderCursedPirate(RenderManager renderManager) {
		super(renderManager, SKELETON_MODEL, 0.3F);
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerBipedArmor(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPirateCursed entity) {
		return entity.isSkeleton() ? SKELETON : entity.getRank().getSkin(entity.getSkinIndex());
	}

	@Override
	protected void renderModel(EntityPirateCursed entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		this.bindTexture(this.getEntityTexture(entity));
		ModelBase model = this.getModel(entity);
		model.isChild = entity.isChild();
		model.isRiding = entity.isRiding();
		model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
	}

	public ModelBase getModel(EntityPirateCursed pirate) {
		return pirate.isSkeleton() ? SKELETON_MODEL : STEVE;
	}
}
