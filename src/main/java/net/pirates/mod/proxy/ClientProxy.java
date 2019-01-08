package net.pirates.mod.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.pirates.mod.client.renderers.RenderBoatSling;
import net.pirates.mod.client.renderers.RenderCannonball;
import net.pirates.mod.client.renderers.RenderCell;
import net.pirates.mod.client.renderers.RenderCleat;
import net.pirates.mod.client.renderers.RendererInvis;
import net.pirates.mod.entity.EntityBullet;
import net.pirates.mod.entity.EntityCannonball;
import net.pirates.mod.entity.EntityPirate;
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
	}

}
