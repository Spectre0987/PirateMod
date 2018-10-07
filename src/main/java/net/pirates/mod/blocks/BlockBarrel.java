package net.pirates.mod.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.items.PItems;
import net.pirates.mod.tileentity.TileEntityBarrel;

public class BlockBarrel extends BlockContainer {

	public BlockBarrel() {
		super(Material.WOOD);
		this.setCreativeTab(Pirate.tab);
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
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBarrel();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		TileEntityBarrel barrel = (TileEntityBarrel)worldIn.getTileEntity(pos);
		if(stack.getItem() == Items.WATER_BUCKET) {
			stack.shrink(1);
			playerIn.addItemStackToInventory(new ItemStack(Items.BUCKET));
			barrel.setWater(barrel.getWater() + 1);
		}
		else if(stack.getItem() == Items.SUGAR) {
			stack.shrink(1);
			barrel.setSugarAmount(barrel.getSugarCount() + 1);
		}
		else if(stack.getItem() == Items.GLASS_BOTTLE && barrel.getRumAmount() >= 5) {
			stack.shrink(1);
			if(!worldIn.isRemote) InventoryHelper.spawnItemStack(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(PItems.rum));
			barrel.setRumAmount(barrel.getRumAmount() - 5);
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

}
