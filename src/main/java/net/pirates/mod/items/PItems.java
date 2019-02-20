package net.pirates.mod.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import net.pirates.mod.Pirate;
import net.pirates.mod.entity.EntityDingy;

@ObjectHolder(Pirate.MODID)
@Mod.EventBusSubscriber(modid = Pirate.MODID, bus = Bus.MOD)
public class PItems {
	
	public static List<Item> items = new ArrayList<Item>();
	
	public static Item pirateHat;
	public static Item pirateBoots;
	public static Item cutlass;
	public static Item flintlock;
	public static Item powder_flask;
	public static Item rum = register(new ItemRumBottle(), "rum");
	public static Item telescope = register(new ItemTelescope(), "telescope");
	//public static Item map = register(new ItemPirateMap(), "map");
	//public static Item message_bottle = register(new ItemMessageBottle(), "message_bottle");
	public static Item compass = register(new ItemPirateCompass(), "compass");
	public static Item sextant = register(new ItemSextant(), "sextant");
	public static Item lantern = register(new ItemLantern(), "lantern");
	public static Item ram_rod = register(new ItemDamageable(250), "ram_rod");
	public static Item hurdy_gurdy = register(new ItemHurdyGurdy(), "hurdy_gurdy");
	public static Item rope = register(new ItemRope(), "rope");
	public static Item grapple_hook = register(new ItemGrapple(), "grapple_hook");
	public static Item sash = register(new ItemSash(), "sash");
	public static Item dingy = register(new ItemESpawner(EntityDingy::new), "dingy");
	//public static Item dagger = register(new ItemSword(ToolMaterial.IRON), "dagger");
	
	public static Item register(Item item, String name) {
		item.setRegistryName(new ResourceLocation(Pirate.MODID, name));
		items.add(item);
		return item;
	}
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<Item> event) {
		for(Item item : items) {
			event.getRegistry().register(item);
		}
	}

}
