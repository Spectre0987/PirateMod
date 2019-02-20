package net.pirates.mod.items;

import net.minecraft.item.Item;
import net.pirates.mod.Pirate;

public class ItemDamageable extends Item {
	
	public ItemDamageable(int dam) {
		super(new Properties().group(Pirate.tab).maxStackSize(1).defaultMaxDamage(dam));
	}

}
