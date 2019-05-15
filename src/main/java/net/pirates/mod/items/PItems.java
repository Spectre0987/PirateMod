package net.pirates.mod.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.Pirate;
import net.pirates.mod.entity.EntityDingy;

public class PItems {
	
	public static List<Item> items = new ArrayList<Item>();
	
	public static Item pirateBoots;
	public static Item cutlass;
	public static Item flintlock = register(new ItemFlintlock(), "flintlock");
	public static Item powder_flask;
	public static Item rum = register(new ItemRumBottle(), "rum");
	public static Item telescope = register(new ItemTelescope(), "telescope");
	public static Item map = register(new ItemPirateMap(), "map");
	public static Item compass = register(new ItemPirateCompass(), "compass");
	public static Item sextant = register(new ItemSextant(), "sextant");
	public static Item lantern = register(new ItemLantern(), "lantern");
	public static Item ram_rod = register(new ItemDamageable(250), "ram_rod");
	public static Item hurdy_gurdy = register(new ItemHurdyGurdy(), "hurdy_gurdy");
	public static Item rope = register(new ItemRope(), "rope");
	public static Item dingy = register(new ItemESpawner(EntityDingy::new), "dingy");
	public static Item dagger = register(new ItemSword(ToolMaterial.IRON).setCreativeTab(Pirate.tab), "dagger");
	public static Item pirateHat = register(new ItemPirateClothes(0), "hat");
	public static Item barbossaHat = register(new ItemPirateClothes(1), "barbossa_hat");
	public static Item bucket = register(new ItemDamageable(150), "bucket");
	public static Item hammer = register(new ItemDamageable(150), "hammer");
	public static Item flintlock_ball = register(new ItemBase(), "flintlock_ball");
	public static Item captain_cutlass = register(new ItemSword(ToolMaterial.DIAMOND).setCreativeTab(Pirate.tab), "captain_cutlass");
	public static Item glass = register(new ItemBase(), "glass_full");
	public static Item rapier = register(new ItemMelee(ToolMaterial.IRON, -1), "rapier");
	
	public static void register() {
		
		cutlass = register(new ItemSword(ToolMaterial.DIAMOND).setCreativeTab(Pirate.tab), "cutlass");
		
		powder_flask = register(new ItemPowderFlask().setCreativeTab(Pirate.tab), "powder_flask");
		
	}
	
	public static Item register(Item item, String name) {
		item.setRegistryName(new ResourceLocation(Pirate.MODID, name));
		item.setTranslationKey(Pirate.MODID + "." + name);
		items.add(item);
		return item;
	}

}
