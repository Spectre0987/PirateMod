package net.pirates.mod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemPowderFlask extends Item {
	
	public ItemPowderFlask() {
		this.setCreativeTab(CreativeTabs.COMBAT);
		this.setMaxDamage(20);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			ItemStack stack = playerIn.getHeldItem(handIn);
			if(playerIn.isSneaking()) {
				if(playerIn.inventory.hasItemStack(new ItemStack(Items.GUNPOWDER)) && stack.getItemDamage() > 0) {
					playerIn.getHeldItem(handIn).damageItem(-1, playerIn);
					for(ItemStack s : playerIn.inventory.mainInventory) {
						if(s.getItem() == Items.GUNPOWDER) {
							s.shrink(1);
							return super.onItemRightClick(worldIn, playerIn, handIn);
						}
					}
				}
			}
			else if(playerIn.inventory.hasItemStack(new ItemStack(PItems.flintlock)) && stack.getItemDamage() < stack.getMaxDamage()){
				
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target,EnumHand hand) {
		return super.itemInteractionForEntity(stack, playerIn, target, hand);
	}

}
