package net.pirates.mod.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;

public class BlockRailing extends BlockHorizontal {

	public static final PropertyBool END = PropertyBool.create("end");
	public static final AxisAlignedBB NORTH_SOUTH = new AxisAlignedBB(0.4, 0, 0, 0.6, 1, 1);
	public static final AxisAlignedBB EAST_WEST = new AxisAlignedBB(0, 0, 0.4, 1, 1, 0.6);
	
	public BlockRailing() {
		super(Material.WOOD);
		this.setCreativeTab(Pirate.tab);
		this.setLightOpacity(0);
		this.setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
		this.setHarvestLevel("axe", 0);
		this.setHardness(1F);
		this.setResistance(1F);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING, END);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.offset(state.getValue(FACING))).getMaterial() != Material.AIR ? state.withProperty(END, false) : state.withProperty(END, true);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
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
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = state.getValue(FACING);
		if(face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
			return NORTH_SOUTH;
		}
		return EAST_WEST;
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(this));
		return list;
	}
}
