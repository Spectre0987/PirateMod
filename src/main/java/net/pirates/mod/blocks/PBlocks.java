package net.pirates.mod.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.pirates.mod.Pirate;
import net.pirates.mod.items.PItems;

@EventBusSubscriber(modid = Pirate.MODID, bus = Bus.MOD)
public class PBlocks {
	
	public static List<Block> BLOCKS = new ArrayList<Block>();
	
	public static Block rail = register(new BlockRailing(), "rail");
	public static Block cannon = register(new BlockCannon(), "cannon");
	public static Block cell = register(new BlockCell(), "cell");
	public static Block ratlines = register(new BlockRatlines(), "ratlines");
	public static Block boat_sling = register(new BlockSling(), "boat_sling");
	public static Block ship_bell = register(new BlockBell(), "ship_bell");
	public static Block barrel = register(new BlockBarrel(), "barrel");
	public static Block ghastly_planks = register(new BlockWood(), "ghastly_planks");
	public static Block ghastly_rail = register(new BlockGhastRailing(), "ghastly_rail");
	public static Block ghastly_log = register(new BlockLogs(), "ghastly_log");
	public static Block ghastly_stairs = register(new BlockPirateStairs(ghastly_log.getDefaultState()), "ghastly_stairs");
	public static Block ghastly_bell = register(new BlockGhostBell(), "ghastly_bell");
	public static Block pirate_chest = register(new BlockPirateChest(), "pirate_chest");
	public static Block forge = register(new BlockForge(), "forge");
	public static Block cannonball = register(new BlockCannonball(), "cannonball");
	public static Block square_sail = register(new BlockSquareSail(), "square_sail");
	public static Block jib_sheet = register(new BlockJibsheet(), "jib_sheet");
	public static Block cleat = register(new BlockCleat(), "cleat");
	public static Block capstan = register(new BlockCapstan(), "capstan");
	public static Block pully = register(new BlockPulley(), "pully");
	public static Block ship_wheel = register(new BlockWheel(), "ship_wheel");
	
	public static Block light_te = register(new BlockLight(), "light_te");	
	
	
	public static Block register(Block item, String name) {
		ResourceLocation loc = new ResourceLocation(name);
		item.setRegistryName(loc);
		BLOCKS.add(item);
		if(!(item instanceof INeedItem))PItems.items.add(new ItemBlock(item, new Properties().group(Pirate.tab)).setRegistryName(loc));
		else PItems.items.add(((INeedItem)item).getItem().setRegistryName(loc));
		return item;
	}
	
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event){
		for(Block block : BLOCKS) {
			event.getRegistry().register(block);
		}
	}

}
