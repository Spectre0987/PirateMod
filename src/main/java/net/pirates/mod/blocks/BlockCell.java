package net.pirates.mod.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.pirates.mod.tileentity.TileEntityCell;

public class BlockCell extends BlockContainer {

	public static final AxisAlignedBB BB = new AxisAlignedBB(0.01, 0, 0.01, 0.99, 0.1, 0.99);

	public BlockCell() {
		super(PirateBlockProperties.BASE);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new TileEntityCell();
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityWalk(worldIn, pos, entityIn);
		TileEntityCell cell = (TileEntityCell) worldIn.getTileEntity(pos);
		if(cell != null && cell.getEntityTag().isEmpty() && !(entityIn instanceof EntityPlayer)) {
			cell.setEntity(entityIn);
			entityIn.remove();
		}
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityCell cell = (TileEntityCell)worldIn.getTileEntity(pos);
		if(!worldIn.isRemote && cell != null && player.isSneaking()) {
			BlockPos off = pos.offset(side);
			Entity e = cell.getEntity();
			e.setPosition(off.getX() + 0.5, off.getY() + 1, off.getZ() + 0.5);
			worldIn.spawnEntity(e);
			cell.setEntity(null);
		}
		return true;
	}

	@Override
	public void onEntityCollision(IBlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityCollision(state, worldIn, pos, entityIn);
		TileEntityCell cell = (TileEntityCell) worldIn.getTileEntity(pos);
		if(cell != null && cell.getEntityTag().isEmpty() && !(entityIn instanceof EntityPlayer)) {
			cell.setEntity(entityIn);
			entityIn.remove();
		}
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
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
