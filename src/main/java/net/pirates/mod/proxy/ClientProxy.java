package net.pirates.mod.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.pirates.mod.client.renderers.RenderBoatSling;
import net.pirates.mod.client.renderers.RenderCannonball;
import net.pirates.mod.client.renderers.RenderCell;
import net.pirates.mod.client.renderers.RenderCleat;
import net.pirates.mod.client.renderers.RenderHumanPirate;
import net.pirates.mod.client.renderers.RenderKraken;
import net.pirates.mod.client.renderers.RenderMermaid;
import net.pirates.mod.client.renderers.RenderShark;
import net.pirates.mod.client.renderers.RendererInvis;
import net.pirates.mod.entity.EntityBullet;
import net.pirates.mod.entity.EntityCannonball;
import net.pirates.mod.entity.EntityKraken;
import net.pirates.mod.entity.EntityMermaid;
import net.pirates.mod.entity.EntityPirate;
import net.pirates.mod.entity.EntityPirateHuman;
import net.pirates.mod.entity.EntityShark;
import net.pirates.mod.tileentity.TileEntityBoatSling;
import net.pirates.mod.tileentity.TileEntityCell;
import net.pirates.mod.tileentity.TileEntityCleat;

public class ClientProxy extends ServerProxy{

	@Override
	public void registerRenderers() {
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCell.class, new RenderCell());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoatSling.class, new RenderBoatSling());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCleat.class, new RenderCleat());
	}

	@Override
	public void preInit() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, RendererInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPirate.class, RenderPirate::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCannonball.class, RenderCannonball::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityShark.class, RenderShark::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPirateHuman.class, RenderHumanPirate::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityKraken.class, RenderKraken::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMermaid.class, RenderMermaid::new);
	}

}
