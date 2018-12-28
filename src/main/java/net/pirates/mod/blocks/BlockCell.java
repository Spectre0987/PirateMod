package net.pirates.mod.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.tileentity.TileEntityCell;

public class BlockCell extends BlockContainer {

	public static final AxisAlignedBB BB = new AxisAlignedBB(0.01, 0, 0.01, 0.99, 0.1, 0.99);

	public BlockCell() {
		super(Material.IRON);
		this.setCreativeTab(Pirate.tab);
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(1F);
		this.setResistance(1F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCell();
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
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityWalk(worldIn, pos, entityIn);
		TileEntityCell cell = (TileEntityCell) worldIn.getTileEntity(pos);
		if(cell != null && cell.getEntityTag().isEmpty() && !(entityIn instanceof EntityPlayer)) {
			cell.setEntity(entityIn);
			entityIn.setDead();
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntityCell cell = (TileEntityCell)worldIn.getTileEntity(pos);
		if(!worldIn.isRemote && cell != null && playerIn.isSneaking()) {
			BlockPos off = pos.offset(facing);
			Entity e = cell.getEntity();
			e.setPosition(off.getX() + 0.5, off.getY() + 1, off.getZ() + 0.5);
			worldIn.spawnEntity(e);
			cell.setEntity(null);
		}
		return true;
	}

	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollision(worldIn, pos, state, entityIn);
		TileEntityCell cell = (TileEntityCell) worldIn.getTileEntity(pos);
		if(cell != null && cell.getEntityTag().isEmpty() && !(entityIn instanceof EntityPlayer)) {
			cell.setEntity(entityIn);
			entityIn.setDead();
		}
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(this));
		return list;
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		TileEntityCell cell = (TileEntityCell)worldIn.getTileEntity(pos);
		if(!worldIn.isRemote && cell != null && cell.getEntity() != null) {
			Entity e = cell.getEntity();	
			e.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
			worldIn.spawnEntity(e);
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}
}
