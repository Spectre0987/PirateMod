package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.pirates.mod.Pirate;

public class BlockWood extends Block {

	public BlockWood() {
		super(Material.WOOD);
		this.setCreativeTab(Pirate.tab);
		this.setHardness(1F);
		this.setResistance(1F);
		this.setHarvestLevel("axe", 0);
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return state.getBlock() != PBlocks.ghastly_planks;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return state.getBlock() != PBlocks.ghastly_planks;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return this == PBlocks.ghastly_planks ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,EnumFacing side) {
		return blockAccess.getBlockState(pos.offset(side)).getBlock() == PBlocks.ghastly_planks ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}

}
