package net.pirates.mod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.entity.EntityGrappleHook;

public class ItemGrapple extends Item{
	
	public ItemGrapple() {
		this.setCreativeTab(Pirate.tab);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			EntityGrappleHook hook = new EntityGrappleHook(worldIn, playerIn);
			hook.setPosition(playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ);
			worldIn.spawnEntity(hook);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}