package net.pirates.mod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;

public class ItemMessageBottle extends Item {
	
	public ItemMessageBottle() {
		this.setCreativeTab(Pirate.tab);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			playerIn.getHeldItem(handIn).shrink(1);
		//	InventoryHelper.spawnItemStack(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(PItems.map));
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
