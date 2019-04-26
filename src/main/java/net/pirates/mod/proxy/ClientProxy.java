package net.pirates.mod.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.pirates.mod.client.renderers.RenderBoatSling;
import net.pirates.mod.client.renderers.RenderCannonball;
import net.pirates.mod.client.renderers.RenderCell;
import net.pirates.mod.client.renderers.RenderCleat;
import net.pirates.mod.client.renderers.RenderDingy;
import net.pirates.mod.client.renderers.RenderGhostPirate;
import net.pirates.mod.client.renderers.RenderKraken;
import net.pirates.mod.client.renderers.RenderMermaid;
import net.pirates.mod.client.renderers.RenderPirateHuman;
import net.pirates.mod.client.renderers.RenderShark;
import net.pirates.mod.client.renderers.RenderWater;
import net.pirates.mod.client.renderers.RendererInvis;
import net.pirates.mod.entity.EntityBullet;
import net.pirates.mod.entity.EntityCannonball;
import net.pirates.mod.entity.EntityDingy;
import net.pirates.mod.entity.EntityGhostPirate;
import net.pirates.mod.entity.EntityKraken;
import net.pirates.mod.entity.EntityMermaid;
import net.pirates.mod.entity.EntityPirateVillager;
import net.pirates.mod.entity.EntityShark;
import net.pirates.mod.entity.EntityWater;
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
		RenderingRegistry.registerEntityRenderingHandler(EntityGhostPirate.class, RenderGhostPirate::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCannonball.class, RenderCannonball::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityShark.class, RenderShark::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPirateVillager.class, RenderPirateHuman::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityKraken.class, RenderKraken::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMermaid.class, RenderMermaid::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDingy.class, RenderDingy::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityWater.class, RenderWater::new);
	}

}
