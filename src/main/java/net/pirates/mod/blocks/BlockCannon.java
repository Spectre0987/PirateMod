package net.pirates.mod.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.entity.EntityCannonball;
import net.pirates.mod.helpers.Helper;

public class BlockCannon extends BlockHorizontal {
	
	public static final AxisAlignedBB BB = new AxisAlignedBB(0, 0, 0, 1, 0.6, 1);

	public BlockCannon() {
		super(Material.IRON);
		this.setCreativeTab(Pirate.tab);
		this.setHarvestLevel("axe", 0);
		this.setHardness(1F);
		this.setResistance(1F);
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
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			Vec3d look = Helper.getVecFromFace(worldIn.getBlockState(pos).getValue(FACING)).scale(15);
			EntityCannonball ball = new EntityCannonball(worldIn, look.x, look.y, look.z);
			pos = pos.offset(worldIn.getBlockState(pos).getValue(FACING));
			ball.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
			worldIn.spawnEntity(ball);
			worldIn.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1F, 1F);
		}
		return true;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(this));
		return list;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return BB;
	}

}
