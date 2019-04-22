package net.pirates.mod.client.renderers;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.Pirate;
import net.pirates.mod.entity.EntityPirateHuman;

public class RenderHumanPirate extends RenderLiving<EntityPirateHuman>{

	public static final ModelPlayer model = new ModelPlayer(0.0625F, false);
	public static final ResourceLocation DECKHAND_TEXTURE = new ResourceLocation(Pirate.MODID, "textures/entity/deckhand.png");
	
	public RenderHumanPirate(RenderManager rendermanagerIn) {
		super(rendermanagerIn, model, 0.5F);
		this.addLayer(new LayerHeldItem(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPirateHuman entity) {
		return DECKHAND_TEXTURE;
	}

}
