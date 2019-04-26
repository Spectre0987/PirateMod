package net.pirates.mod.client.renderers;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.Pirate;
import net.pirates.mod.entity.EntityPirateVillager;

public class RenderPirateHuman extends RenderLiving<EntityPirateVillager>{

	public static final ModelPlayer model = new ModelPlayer(0.0625F, false);
	public static final ResourceLocation DECKHAND_TEXTURE = new ResourceLocation(Pirate.MODID, "textures/entity/pirates/pirate_midship_001.png");
	
	public RenderPirateHuman(RenderManager rendermanagerIn) {
		super(rendermanagerIn, model, 0.5F);
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerBipedArmor(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPirateVillager entity) {
		return DECKHAND_TEXTURE;
	}

}
