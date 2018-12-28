package net.pirates.mod.blocks;

import net.minecraft.util.BlockRenderLayer;

public class BlockGhostBell extends BlockBell {

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

}
