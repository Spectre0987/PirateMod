package net.pirates.mod.misc;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.pirates.mod.Pirate;

@Mod.EventBusSubscriber(modid = Pirate.MODID)
public class WorldData {

	
	
	@SubscribeEvent
	public static void updateWind(WorldTickEvent event) {
		
	}
}
