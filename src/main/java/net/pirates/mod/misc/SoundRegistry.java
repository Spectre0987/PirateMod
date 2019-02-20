package net.pirates.mod.misc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.pirates.mod.Pirate;

@EventBusSubscriber(modid = Pirate.MODID, bus = Bus.MOD)
public class SoundRegistry {
	
	private static List<SoundEvent> SOUNDS = new ArrayList<SoundEvent>();
	
	public static SoundEvent ship_bell = register("ship_bell");
	public static SoundEvent telescope_open = register("telescope_open");
	
	private static SoundEvent register(String name) {
		ResourceLocation loc = new ResourceLocation(Pirate.MODID, name);
		SoundEvent sound = new SoundEvent(loc);
		sound.setRegistryName(loc);
		SOUNDS.add(sound);
		return sound;
	}
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		for(SoundEvent se : SOUNDS) {
			event.getRegistry().register(se);
		}
	}

}
