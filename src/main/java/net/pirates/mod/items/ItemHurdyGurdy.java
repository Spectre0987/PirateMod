package net.pirates.mod.items;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.pirates.mod.Pirate;

public class ItemHurdyGurdy extends Item {
	
	public ItemHurdyGurdy() {
		this.setCreativeTab(Pirate.tab);
		this.setMaxStackSize(1);
	}

}
