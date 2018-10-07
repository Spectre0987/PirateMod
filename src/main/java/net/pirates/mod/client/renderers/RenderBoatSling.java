package net.pirates.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.pirates.mod.tileentity.TileEntityBoatSling;

public class RenderBoatSling extends TileEntitySpecialRenderer<TileEntityBoatSling>{

	@Override
	public void render(TileEntityBoatSling te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if(te.getBoat() != null) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + 0.5, y - 0.5, z + 0.5);
			Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(te.getBoat()).doRender(te.getBoat(), 0, 0, 0, 0, 0);
			GlStateManager.popMatrix();
		}
	}

}
