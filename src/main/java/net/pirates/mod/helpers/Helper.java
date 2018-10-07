package net.pirates.mod.helpers;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;

public class Helper {

	public static Vec3d getVecFromFace(EnumFacing facing) {
		switch(facing) {
		case NORTH: return new Vec3d(0, 0, -1);
		case SOUTH: return new Vec3d(0, 0, 1);
		case UP: return new Vec3d(0, 1, 0);
		case DOWN: return new Vec3d(0, -1, 0);
		case EAST: return new Vec3d(1, 0, 0);
		case WEST: return new Vec3d(-1, 0, 0);
		}
		return new Vec3d(0, 0, 0);
	}

	public static NBTTagCompound getTag(ItemStack stack) {
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		return stack.getTagCompound();
	}

	public static void drawTexturedQuad(int x, int y, int width, int height) {
		GlStateManager.pushMatrix();
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		bb.pos(x, y, 0).tex(0, 0).endVertex();
		bb.pos(x, y + height, 0).tex(0, 1).endVertex();
		bb.pos(x + width, y + height, 0).tex(1, 1).endVertex();
		bb.pos(x + width, y, 0).tex(1, 0).endVertex();
		Tessellator.getInstance().draw();
		GlStateManager.popMatrix();
	}

	public static void drawColoredQuad(int x, int y, int width, int height, float r, float g, float b) {
		GlStateManager.pushMatrix();
		GlStateManager.disableTexture2D();
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
		bb.pos(x, y, 0).color(r, g, b, 1F).endVertex();
		bb.pos(x, y + height, 0).color(r, g, b, 1F).endVertex();
		bb.pos(x + width, y + height, 0).color(r, g, b, 1F).endVertex();
		bb.pos(x + width, y, 0).color(r, g, b, 1F).endVertex();
		Tessellator.getInstance().draw();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
	}
}
