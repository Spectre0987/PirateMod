package net.pirates.mod.client.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.entity.EntityCannonball;

public class RenderCannonball extends Render<EntityCannonball>{

	public RenderCannonball(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityCannonball entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		bb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
			IBlockState state = Blocks.STONE.getDefaultState();
			IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);
			for(EnumFacing face : EnumFacing.VALUES) {
				for(BakedQuad quad : model.getQuads(state, face, 0)) {
					bb.addVertexData(quad.getVertexData());
				}
			}
		Tessellator.getInstance().draw();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCannonball entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

}
