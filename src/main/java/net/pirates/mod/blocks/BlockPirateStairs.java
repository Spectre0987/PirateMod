package net.pirates.mod.blocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.pirates.mod.Pirate;

public class BlockPirateStairs extends BlockStairs {

	protected BlockPirateStairs(IBlockState modelState) {
		super(modelState);
		this.setCreativeTab(Pirate.tab);
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return blockAccess.getBlockState(pos.offset(side)).getBlock() == PBlocks.ghastly_stairs ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}


}
