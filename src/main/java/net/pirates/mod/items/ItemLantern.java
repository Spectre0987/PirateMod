package net.pirates.mod.items;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.blocks.PBlocks;
import net.pirates.mod.helpers.Helper;
import net.pirates.mod.tileentity.TileEntityLight;

public class ItemLantern extends Item {
	
	public ItemLantern() {
		this.setCreativeTab(Pirate.tab);
		this.setMaxStackSize(1);
	}

	
	public static void setupNewLantern(EntityPlayer player, ItemStack lantern) {
		NBTTagCompound tag = Helper.getTag(lantern);
		tag.setBoolean("is_on", false);
		lantern.setTagCompound(tag);
	}


	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		setOn(stack, !isOn(stack));
		if(!worldIn.isRemote && isOn(stack)) {
			//Spawn lantern TE
			worldIn.setBlockState(playerIn.getPosition().up(), PBlocks.light_te.getDefaultState());
			TileEntityLight light = (TileEntityLight)worldIn.getTileEntity(playerIn.getPosition().up());
			if(light != null)light.setPlayerID(playerIn.getUniqueID());
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	public static boolean isOn(ItemStack lantern) {
		return Helper.getTag(lantern).getBoolean("is_on");
	}
	
	public static void setOn(ItemStack stack, boolean isOn) {
		Helper.getTag(stack).setBoolean("is_on", isOn);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(stack.getTagCompound() == null && entityIn instanceof EntityPlayer) {
			setupNewLantern(((EntityPlayer)entityIn), stack);
		}
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}
}
