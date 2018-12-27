package net.pirates.mod.items;

import net.minecraft.item.Item;
import net.pirates.mod.Pirate;

public class ItemDamageable extends Item {
	
	public ItemDamageable(int dam) {
		this.setCreativeTab(Pirate.tab);
		this.setMaxDamage(dam);
		this.setMaxStackSize(1);
	}

}
