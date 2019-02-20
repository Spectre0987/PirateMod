package net.pirates.mod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemSextant extends Item {
	
	public ItemSextant() {
		super(PirateItemProperties.BASE_ONE);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			playerIn.sendStatusMessage(new TextComponentString("X: " + (int)playerIn.posX + ", Z: " + (int) playerIn.posZ), true);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
