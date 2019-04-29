package net.pirates.mod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pirates.mod.tileentity.TileEntityLiquorRack;

public class BlockLiquorRack extends BlockTileBase {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public BlockLiquorRack() {
		super(Material.WOOD, TileEntityLiquorRack::new);
		this.setLightOpacity(0);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			ItemStack held = playerIn.getHeldItem(hand);
			TileEntity te = worldIn.getTileEntity(pos);
			if(te instanceof TileEntityLiquorRack) {
				TileEntityLiquorRack rack = (TileEntityLiquorRack)te;
				if(held.getItemUseAction() == EnumAction.DRINK) {
					rack.addBottle(held);
					if(!playerIn.isCreative())
						playerIn.setHeldItem(hand, ItemStack.EMPTY);
					return true;
				}
				else if(held.isEmpty()) {
					rack.removeBottle(playerIn);
				}
			}
		}
		return false;
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
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

}
