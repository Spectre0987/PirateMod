package net.pirates.mod.misc;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.pirates.mod.Pirate;

@EventBusSubscriber(modid = Pirate.MODID)
public class Recipes {
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<IRecipe> event) {
		event.getRegistry().register(new RecipeTelescope());
	}

}
