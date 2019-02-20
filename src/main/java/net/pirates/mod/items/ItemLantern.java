package net.pirates.mod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemLantern extends Item {
	
	public ItemLantern() {
		super(PirateItemProperties.BASE_ONE);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
}
