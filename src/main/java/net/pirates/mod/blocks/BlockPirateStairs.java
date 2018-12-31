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
		this.setHardness(1.5F);
		this.setHarvestLevel("axe", -1);
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}


}
