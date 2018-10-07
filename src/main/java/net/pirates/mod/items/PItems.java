package net.pirates.mod.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.Pirate;

public class PItems {
	
	public static List<Item> items = new ArrayList<Item>();
	
	public static Item pirateHat;
	public static Item pirateBoots;
	public static Item cutlass;
	public static Item flintlock;
	public static Item powder_flask;
	public static Item rum = register(new ItemRumBottle(), "rum");
	public static Item telescope = register(new ItemTelescope(), "telescope");
	
	public static void register() {
		
		pirateHat = register(new ItemPirateArmor(0, EntityEquipmentSlot.HEAD), "pirate_hat");
		
		pirateBoots = register(new ItemPirateArmor(3, EntityEquipmentSlot.FEET), "pirate_boots");
		
		cutlass = register(new ItemSword(ToolMaterial.DIAMOND).setCreativeTab(Pirate.tab), "cutlass");
		
		flintlock = register(new ItemFlintlock().setCreativeTab(Pirate.tab), "flintlock");
		
		powder_flask = register(new ItemPowderFlask().setCreativeTab(Pirate.tab), "powder_flask");
		
	}
	
	public static Item register(Item item, String name) {
		item.setRegistryName(new ResourceLocation(Pirate.MODID, name));
		item.setUnlocalizedName(Pirate.MODID + "." + name);
		items.add(item);
		return item;
	}

}
