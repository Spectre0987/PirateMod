package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;

public class BlockRatlines extends BlockHorizontal {

	public static final PropertyBool END = PropertyBool.create("end");
	
	public BlockRatlines() {
		super(Material.CLOTH);
		this.setCreativeTab(Pirate.tab);
		this.setHardness(0.5F);
		this.setResistance(0.5F);
		this.setHarvestLevel("shears", 0);
		this.setLightOpacity(0);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(END, worldIn.getBlockState(pos.down()).getBlock() != PBlocks.ratlines);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch(state.getValue(FACING)){
			case NORTH: return new AxisAlignedBB(0, 0, 0.9, 1, 1, 1);
			case EAST: return new AxisAlignedBB(0, 0, 0, 0.1, 1, 1);
			case SOUTH : return new AxisAlignedBB(0, 0, 0, 1, 1, 0.1);
			case WEST: return new AxisAlignedBB(0.9, 0, 0, 1, 1, 1);
			default: return Block.FULL_BLOCK_AABB;
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING, END);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return this.getBoundingBox(blockState, worldIn, pos);
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
	public boolean causesSuffocation(IBlockState state) {
		return false;
	}

}
