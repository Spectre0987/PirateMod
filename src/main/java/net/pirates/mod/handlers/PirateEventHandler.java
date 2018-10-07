package net.pirates.mod.handlers;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.pirates.mod.Pirate;
import net.pirates.mod.items.PItems;

@Mod.EventBusSubscriber(modid = Pirate.MODID)
public class PirateEventHandler {
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for(Item item : PItems.items) {
			event.getRegistry().register(item);
		}
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for(Item i : PItems.items) {
			ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
		}
	}
}
