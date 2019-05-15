package net.pirates.mod.helpers;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.pirates.mod.config.PirateConfig;

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

	public static void drawTexturedQuad(double x, double y, double width, double height, double minU, double minV, double maxU, double maxV) {
		GlStateManager.pushMatrix();
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		bb.pos(x, y, 0).tex(minU, minV).endVertex();
		bb.pos(x, y + height, 0).tex(minU, maxV).endVertex();
		bb.pos(x + width, y + height, 0).tex(maxU, maxV).endVertex();
		bb.pos(x + width, y, 0).tex(maxU, minV).endVertex();
		Tessellator.getInstance().draw();
		GlStateManager.popMatrix();
	}

	public static void drawColoredQuad(double x, double y, double width, double height, float r, float g, float b) {
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

	public static boolean biomeEnabled(ResourceLocation key) {
		for(String s : PirateConfig.worldGen.biomes) {
			if(s.equals(key.toString()))
				return true;
		}
		return false;
	}
}
