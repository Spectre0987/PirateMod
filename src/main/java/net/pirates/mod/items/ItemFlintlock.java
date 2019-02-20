package net.pirates.mod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;

public class ItemFlintlock extends Item {
	
	public ItemFlintlock() {
		super(new Properties().group(Pirate.tab).defaultMaxDamage(500).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
