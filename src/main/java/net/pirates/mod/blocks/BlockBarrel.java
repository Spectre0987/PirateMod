package net.pirates.mod.blocks;

import net.minecraft.block.Block;
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
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.pirates.mod.blocks.prop.PirateBlockProperties;
import net.pirates.mod.items.PItems;
import net.pirates.mod.tileentity.TileEntityBarrel;

public class BlockBarrel extends Block {

	public BlockBarrel() {
		super(PirateBlockProperties.BASE);
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
		return new TileEntityBarrel();
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		TileEntityBarrel barrel = (TileEntityBarrel)worldIn.getTileEntity(pos);
		if(stack.getItem() == Items.WATER_BUCKET) {
			stack.shrink(1);
			player.addItemStackToInventory(new ItemStack(Items.BUCKET));
			barrel.setWater(barrel.getWater() + 1);
		}
		else if(stack.getItem() == Items.SUGAR) {
			stack.shrink(1);
			barrel.setSugarAmount(barrel.getSugarCount() + 1);
		}
		else if(stack.getItem() == Items.GLASS_BOTTLE && barrel.getRumAmount() >= 5) {
			stack.shrink(1);
			if(!worldIn.isRemote) InventoryHelper.spawnItemStack(worldIn, player.posX, player.posY, player.posZ, new ItemStack(PItems.rum));
			barrel.setRumAmount(barrel.getRumAmount() - 5);
		}
		return super.onBlockActivated(state, worldIn, pos, player, hand, side, hitX, hitY, hitZ);
	}

}
