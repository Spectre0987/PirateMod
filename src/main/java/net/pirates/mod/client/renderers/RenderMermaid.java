package net.pirates.mod.client.renderers;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.Pirate;
import net.pirates.mod.client.models.ModelMermaid;
import net.pirates.mod.entity.EntityMermaid;

public class RenderMermaid extends RenderLiving<EntityMermaid>{

	public static final ModelMermaid model = new ModelMermaid();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Pirate.MODID, "textures/entity/mermaid.png");
	
	public RenderMermaid(RenderManager rendermanagerIn) {
		super(rendermanagerIn, model, 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMermaid entity) {
		return TEXTURE;
	}

}
