package net.pirates.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.blocks.BlockLiquorRack;
import net.pirates.mod.tileentity.TileEntityLiquorRack;

public class RenderLiquorRack extends TileEntitySpecialRenderer<TileEntityLiquorRack> {

	@Override
	public void render(TileEntityLiquorRack te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y, z + 0.5);
		GlStateManager.rotate(-te.getWorld().getBlockState(te.getPos()).getValue(BlockLiquorRack.FACING).getHorizontalAngle() - 180, 0, 1, 0);
		GlStateManager.translate(0.35, 0.75, -0.25);
		GlStateManager.rotate(270, 1, 0, 0);
		for(int i = 0; i < 9; ++i) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(-(i % 3) * 0.30, 0, -(i / 3) * 0.3);
			Minecraft.getMinecraft().getRenderItem().renderItem(te.getStackInSlot(i), TransformType.NONE);
			GlStateManager.popMatrix();
		}
		GlStateManager.popMatrix();
	}

	@Override
	protected void bindTexture(ResourceLocation location) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(location);
	}

}
