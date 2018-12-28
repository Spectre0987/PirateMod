package net.pirates.mod.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.pirates.mod.client.renderers.RenderBoatSling;
import net.pirates.mod.client.renderers.RenderCannonball;
import net.pirates.mod.client.renderers.RenderCell;
import net.pirates.mod.client.renderers.RenderItemBoots;
import net.pirates.mod.client.renderers.RenderItemHat;
import net.pirates.mod.client.renderers.RenderShark;
import net.pirates.mod.client.renderers.RendererInvis;
import net.pirates.mod.entity.EntityBullet;
import net.pirates.mod.entity.EntityCannonball;
import net.pirates.mod.entity.EntityPirate;
import net.pirates.mod.entity.EntityShark;
import net.pirates.mod.items.PItems;
import net.pirates.mod.tileentity.TileEntityBoatSling;
import net.pirates.mod.tileentity.TileEntityCell;

public class ClientProxy extends ServerProxy{

	@Override
	public void registerRenderers() {
		PItems.pirateHat.setTileEntityItemStackRenderer(new RenderItemHat());
		PItems.pirateBoots.setTileEntityItemStackRenderer(new RenderItemBoots());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCell.class, new RenderCell());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoatSling.class, new RenderBoatSling());
	}

	@Override
	public void preInit() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, RendererInvis::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityShark.class, RenderShark::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPirate.class, RenderPirate::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCannonball.class, RenderCannonball::new);
	}

}
