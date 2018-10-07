package net.pirates.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.pirates.mod.tileentity.TileEntityCell;

public class RenderCell extends TileEntitySpecialRenderer<TileEntityCell> {

	@Override
	public void render(TileEntityCell te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y, z + 0.5);
		if(te.getEntity() != null) {
			Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(te.getEntity()).doRender(te.getEntity(), 0, 0, 0, 0, 0);
		}
		GlStateManager.popMatrix();
	}

}
