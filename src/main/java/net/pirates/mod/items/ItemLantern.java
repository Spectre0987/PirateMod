package net.pirates.mod.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.helpers.Helper;

public class ItemLantern extends Item {
	
	public ItemLantern() {
		this.setCreativeTab(Pirate.tab);
		this.setMaxStackSize(1);
	}

}
