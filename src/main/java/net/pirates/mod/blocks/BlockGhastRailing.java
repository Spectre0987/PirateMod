package net.pirates.mod.blocks;

import net.minecraft.util.BlockRenderLayer;

public class BlockGhastRailing extends BlockRailing {

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

}
