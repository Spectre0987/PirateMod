package net.pirates.mod.items;

import net.minecraft.item.Item;
import net.pirates.mod.Pirate;

public class ItemLantern extends Item {
	
	public ItemLantern() {
		this.setCreativeTab(Pirate.tab);
		this.setMaxStackSize(1);
	}

}
