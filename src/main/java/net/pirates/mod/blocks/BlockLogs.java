package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;

public class BlockLogs extends Block {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public BlockLogs() {
		super(Material.WOOD);
		this.setCreativeTab(Pirate.tab);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, facing.getOpposite());
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return state.getBlock() != PBlocks.ghastly_log;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return blockAccess.getBlockState(pos.offset(side)).getBlock() == PBlocks.ghastly_log ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return state.getBlock() != PBlocks.ghastly_log;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

}
