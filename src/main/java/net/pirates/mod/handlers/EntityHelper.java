package net.pirates.mod.handlers;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.pirates.mod.Pirate;

public class EntityHelper {

	private static int id = 0;
	
	public static void registerProjectiles(Class clazz, String name) {
		EntityRegistry.registerModEntity(new ResourceLocation(Pirate.MODID, name), clazz, name, id++, Pirate.instance, 120, 5, true);
	}

	public static void registerWaterMobs(Class clazz, String name) {
		EntityRegistry.registerModEntity(new ResourceLocation(Pirate.MODID, name), clazz, name, id++, Pirate.instance, 120, 5, true, 0x00C9FF, 0x000000);
	}

	public static void registerMobs(Class<? extends Entity> class1, String name) {
		EntityRegistry.registerModEntity(new ResourceLocation(Pirate.MODID, name), class1, name, id++, Pirate.instance, 64, 5, true, 0x4C4C4C, 0x2CB9CC);
	}

}
