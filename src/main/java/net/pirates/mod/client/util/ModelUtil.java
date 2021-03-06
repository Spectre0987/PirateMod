package net.pirates.mod.client.util;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class ModelUtil {

	public static void renderRope(double startX, double startY, double startZ, double endX, double endY, double endZ, double size) {
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		double maxY = (endX - startX) + (endY - startY) + (endZ - startZ) / 3;
		
		bb.pos(startX, startY, startZ).tex(0, 0).endVertex();
		bb.pos(startX, startY, startZ + size).tex(1, 0).endVertex();
		bb.pos(startX + endX, startY + endY, startZ + endZ + size).tex(1, maxY).endVertex();
		bb.pos(startX + endX, startY + endY, startZ + endZ).tex(0, maxY).endVertex();
		
		bb.pos(startX, startY, startZ + size).tex(0, 0).endVertex();
		bb.pos(startX + size, startY, startZ + size).tex(1, 0).endVertex();
		bb.pos(startX + size + endX, startY + endY, startZ + endZ + size).tex(1, maxY).endVertex();
		bb.pos(startX + endX, startY + endY, startZ + endZ + size).tex(0, maxY).endVertex();
		
		Tessellator.getInstance().draw();
	}
}
