package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;

public class BlockJibsheet extends Block {
	
	public static PropertyInteger TYPE = PropertyInteger.create("sail", 0, 3);

	public BlockJibsheet() {
		super(Material.CLOTH);
		this.setCreativeTab(Pirate.tab);
		this.setLightOpacity(0);
		this.setHardness(1.5F);
		this.setHarvestLevel("sword", -1);
		this.setDefaultState(this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH).withProperty(TYPE, 0));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.byHorizontalIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
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
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, TYPE, BlockHorizontal.FACING);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing());
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing face = state.getValue(BlockHorizontal.FACING);
		if(worldIn.getBlockState(pos.down()).getBlock() != PBlocks.jib_sheet) {
			if(worldIn.getBlockState(pos.offset(face)).getBlock() != PBlocks.jib_sheet)
				return state.withProperty(TYPE, 1);
			return state.withProperty(TYPE, 2);
		}
		if(worldIn.getBlockState(pos.offset(face)).getBlock() != PBlocks.jib_sheet)
			return state.withProperty(TYPE, 3);
		return super.getActualState(state, worldIn, pos);
	}

}
