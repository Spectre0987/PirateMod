package net.pirates.mod.client.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.Pirate;
import net.pirates.mod.client.util.ModelUtil;
import net.pirates.mod.tileentity.TileEntityCleat;

public class RenderCleat extends TileEntitySpecialRenderer<TileEntityCleat> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Pirate.MODID, "textures/blocks/rope.png");

	@Override
	public void render(TileEntityCleat te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		this.bindTexture(TEXTURE);
		double tx = te.getPos().getX() + 0.5, ty = te.getPos().getY() + 0.5, tz = te.getPos().getZ() + 0.5;
		ModelUtil.renderRope(0, 0, 0, 0, 1, 1.2, 0.1);
		GlStateManager.popMatrix();
	}

	@Override
	protected void bindTexture(ResourceLocation location) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(location);
	}
}
