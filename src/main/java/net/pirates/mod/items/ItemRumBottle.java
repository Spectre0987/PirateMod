package net.pirates.mod.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.capability.CapabilityDrunk;
import net.pirates.mod.capability.DrunkStorage;
import net.pirates.mod.helpers.Helper;

public class ItemRumBottle extends Item {
	
	public ItemRumBottle() {
		this.setCreativeTab(Pirate.tab);
		this.setMaxStackSize(1);
	}
	
	public static void setDrinks(ItemStack stack, int i) {
		Helper.getTag(stack).setInteger("drinks", i);
	}
	
	public static int getDrinks(ItemStack stack) {
		return Helper.getTag(stack).getInteger("drinks");
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(Helper.getTag(stack).hasKey("drinks")) tooltip.add("Swigs left: " + Helper.getTag(stack).getInteger("drinks"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if(!Helper.getTag(stack).hasKey("drinks")) {
			Helper.getTag(stack).setInteger("drinks", 5);
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.setActiveHand(handIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase e) {
		NBTTagCompound tag = Helper.getTag(stack);
		if(tag.getInteger("drinks") > 0) {
			tag.setInteger("drinks", tag.getInteger("drinks") - 1);
		}
		else {
			tag = null;
			stack.shrink(1);
			if(!worldIn.isRemote) InventoryHelper.spawnItemStack(worldIn, e.posX, e.posY, e.posZ, new ItemStack(Items.GLASS_BOTTLE));
		}
		stack.setTagCompound(tag);
		int time = 20 * 25;
		if(e instanceof EntityPlayer && e.hasCapability(DrunkStorage.DRUNK, null)) {
			CapabilityDrunk drunk = e.getCapability(DrunkStorage.DRUNK, null);
			drunk.addDrunkTicks(600);
			e.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, time, drunk.getDrunkTicks() / 100));
			e.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, time, drunk.getDrunkTicks() / 100));
		}
		return super.onItemUseFinish(stack, worldIn, e);
	}

}
