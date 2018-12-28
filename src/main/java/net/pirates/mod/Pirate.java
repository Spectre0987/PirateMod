package net.pirates.mod;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.pirates.mod.blocks.PBlocks;
import net.pirates.mod.capability.CapabilityDrunk;
import net.pirates.mod.capability.DrunkStorage;
import net.pirates.mod.capability.IDrunk;
import net.pirates.mod.entity.EntityBullet;
import net.pirates.mod.entity.EntityCannonball;
import net.pirates.mod.entity.EntityPirate;
import net.pirates.mod.handlers.EntityHelper;
import net.pirates.mod.items.PItems;
import net.pirates.mod.packets.MessageSync;
import net.pirates.mod.proxy.ServerProxy;
import net.pirates.mod.tileentity.TileEntityBarrel;
import net.pirates.mod.tileentity.TileEntityBoatSling;
import net.pirates.mod.tileentity.TileEntityCell;
import net.pirates.mod.tileentity.TileEntityPirateChest;
import net.pirates.mod.worldgen.WorldGenShips;

@Mod(modid = Pirate.MODID, name = Pirate.NAME, version = Pirate.VERSION)
public class Pirate
{
    public static final String MODID = "pirates";
    public static final String NAME = "Pirate Mod";
    public static final String VERSION = "0.0.2";

    private static Logger logger;

    @SidedProxy(serverSide = "net.pirates.mod.proxy.ServerProxy", clientSide = "net.pirates.mod.proxy.ClientProxy")
    public static ServerProxy proxy;
    
    @Instance
    public static Pirate instance = new Pirate();
    
    public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
    
    public static CreativeTabs tab;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {	
    	tab = new CreativeTabs(MODID) {
			@Override
			public ItemStack createIcon() {
				return new ItemStack(PItems.sextant);
			}};
    	PItems.register();
    	PBlocks.register();
    	EntityHelper.registerProjectiles(EntityBullet.class, "bullet");
    	EntityHelper.registerProjectiles(EntityCannonball.class, "cannon_ball");
    	EntityHelper.registerMobs(EntityPirate.class, "pirate");
    	
    	registerTileEntity(TileEntityCell.class, "cell");
    	registerTileEntity(TileEntityBoatSling.class, "boat_sling");
    	registerTileEntity(TileEntityBarrel.class, "barrel");
    	registerTileEntity(TileEntityPirateChest.class, "pirate_chest");
    	
    	proxy.preInit();
    	
    	GameRegistry.registerWorldGenerator(new WorldGenShips(), 0);
    	
    	CapabilityManager.INSTANCE.register(IDrunk.class, new DrunkStorage(), CapabilityDrunk::new);
    	
    	NETWORK.registerMessage(MessageSync.Handler.class, MessageSync.class, 0, Side.CLIENT);
    	
    	LootTableList.register(new ResourceLocation(MODID, "cursed_chest"));
    	
    	LootTableList.register(new ResourceLocation(MODID, "ghost_captain"));
    	//LootTableList.register(new ResourceLocation(MODID, "ghost_chest"));
    }

	@EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.registerRenderers();
    }
	
	private static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
		GameRegistry.registerTileEntity(clazz, new ResourceLocation(Pirate.MODID, name));
	}
}
