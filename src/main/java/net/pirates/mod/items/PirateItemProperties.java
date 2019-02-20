package net.pirates.mod.items;

import net.minecraft.item.Item.Properties;
import net.pirates.mod.Pirate;

public class PirateItemProperties {
	
	public static final Properties BASE_ONE = new Properties().group(Pirate.tab).maxStackSize(1);

	public static Properties getTool(int i) {
		return new Properties().group(Pirate.tab).maxStackSize(1).defaultMaxDamage(i);
	}

}
