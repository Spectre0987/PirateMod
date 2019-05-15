package net.pirates.mod.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.pirates.mod.Pirate;

@Config(modid = "pirates", name = Pirate.NAME)
public class PirateConfig {
	
	@Config.LangKey("pirates.worldgen")
	public static WorldGen worldGen = new WorldGen();
	
	public static class WorldGen{
		
		@Config.LangKey("pirates.worldgen.chance")
		public int chance = 10;
		
		@Config.LangKey("pirates.worldgen.biomes")
		public String[] biomes = {
				"minecraft:deep_ocean"
		};
	}
	
	@EventBusSubscriber
	public static class Event{
		
		@SubscribeEvent
		public static void onConfigChange(ConfigChangedEvent event) {
			if(event.getModID().equals(Pirate.MODID)) {
				ConfigManager.sync(Pirate.MODID, Config.Type.INSTANCE);
			}
		}
	}

}
