package net.pirates.mod.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.entity.EntityBullet;

public class ItemFlintlock extends Item {
	
	public ItemFlintlock() {
		this.setMaxStackSize(1);
		this.setMaxStackSize(ToolMaterial.IRON.getMaxUses());
		this.setCreativeTab(Pirate.tab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			ItemStack held = playerIn.getHeldItem(handIn);
			if(held.getTagCompound() != null && held.getTagCompound().hasKey("data")) {
				FlintlockData data = new FlintlockData(held.getTagCompound().getCompoundTag("data"));
				if(data.ammo.getItem() == PItems.flintlock_ball && data.powder > 0.5) {
					EntityBullet bullet = new EntityBullet(worldIn, playerIn);
					bullet.setPosition(playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ);
					worldIn.spawnEntity(bullet);
					
					data.powder -= 0.5;
					data.ammo = ItemStack.EMPTY;
					held.getTagCompound().setTag("data", data.write());
				}
				else {
					if(data.ammo.isEmpty()) {
						ItemStack ammo = load(EnumLoadType.AMMO, playerIn);
						if(!ammo.isEmpty()) {
							data.ammo = ammo;
						}
						held.getTagCompound().setTag("data", data.write());
					}
				}
			}
			else {
				NBTTagCompound tag = held.getTagCompound() == null ? new NBTTagCompound() : held.getTagCompound();
				ItemStack ammo = load(EnumLoadType.AMMO, playerIn);
				ItemStack powder = load(EnumLoadType.POWDER, playerIn);
				tag.setTag("data", new FlintlockData(ammo, !powder.isEmpty() ? 0.5 : 0).write());
				held.setTagCompound(tag);
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("data")) {
			FlintlockData data = new FlintlockData(stack.getTagCompound());
			if(!data.ammo.isEmpty())
				tooltip.add("Ammo: " + data.ammo.getDisplayName());
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	public static class FlintlockData{
		
		public ItemStack ammo = ItemStack.EMPTY;
		public double powder = 0;
		
		public FlintlockData(ItemStack ammo, double powder) {
			this.ammo = ammo;
			this.powder = powder;
		}
		
		public FlintlockData(NBTTagCompound tag) {
			this.ammo = new ItemStack(tag.getCompoundTag("ammo"));
			this.powder = tag.getDouble("powder");
		}
		
		public NBTTagCompound write() {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setTag("ammo", ammo.serializeNBT());
			tag.setDouble("powder", powder);
			return tag;
		}
	}
	
	public static ItemStack load(EnumLoadType type, EntityPlayer player) {
		for(ItemStack stack : player.inventory.mainInventory) {
			if(type == EnumLoadType.AMMO) {
				if(stack.getItem() == PItems.flintlock_ball) {
					ItemStack newItem = stack.copy();
					stack.shrink(1);
					newItem.setCount(1);
					return newItem;
				}
			}
			else if(type == EnumLoadType.POWDER) {
				if(stack.getItem() == Items.GUNPOWDER) {
					ItemStack newStack = stack.copy();
					stack.shrink(1);
					newStack.setCount(1);
					return newStack;
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static enum EnumLoadType{
		AMMO,
		POWDER
	}
	
}
