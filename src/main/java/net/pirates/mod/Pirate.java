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
import net.pirates.mod.client.ModelRegistry;
import net.pirates.mod.entity.EntityBottle;
import net.pirates.mod.entity.EntityBullet;
import net.pirates.mod.entity.EntityCannonball;
import net.pirates.mod.entity.EntityDingy;
import net.pirates.mod.entity.EntityGhostPirate;
import net.pirates.mod.entity.EntityGrappleHook;
import net.pirates.mod.entity.EntityKraken;
import net.pirates.mod.entity.EntityMermaid;
import net.pirates.mod.entity.EntityPirateVillager;
import net.pirates.mod.entity.EntityPulledBlock;
import net.pirates.mod.entity.EntityShark;
import net.pirates.mod.entity.EntityWater;
import net.pirates.mod.handlers.EntityHelper;
import net.pirates.mod.items.PItems;
import net.pirates.mod.packets.MessageSync;
import net.pirates.mod.packets.MessageTESync;
import net.pirates.mod.proxy.ServerProxy;
import net.pirates.mod.tileentity.TileEntityBarrel;
import net.pirates.mod.tileentity.TileEntityBoatSling;
import net.pirates.mod.tileentity.TileEntityCannon;
import net.pirates.mod.tileentity.TileEntityCell;
import net.pirates.mod.tileentity.TileEntityCleat;
import net.pirates.mod.tileentity.TileEntityForge;
import net.pirates.mod.tileentity.TileEntityLight;
import net.pirates.mod.tileentity.TileEntityLiquorRack;
import net.pirates.mod.tileentity.TileEntityPirateChest;
import net.pirates.mod.tileentity.TileEntityPully;
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
    	EntityHelper.registerProjectiles(EntityBottle.class, "bottle");
    	EntityHelper.registerMobs(EntityGhostPirate.class, "pirate");
    	EntityHelper.registerProjectiles(EntityGrappleHook.class, "grapple_hook");
    	EntityHelper.registerWaterMobs(EntityShark.class, "shark");
    	EntityHelper.registerMobs(EntityPirateVillager.class, "pirate_villager");
    	EntityHelper.registerWaterMobs(EntityKraken.class, "kraken");
    	EntityHelper.registerWaterMobs(EntityMermaid.class, "mermaid");
    	EntityHelper.registerWaterMobs(EntityDingy.class, "dingy");
    	EntityHelper.registerProjectiles(EntityPulledBlock.class, "pulled_block");
    	EntityHelper.registerProjectiles(EntityWater.class, "bucket_water");
    	
    	registerTileEntity(TileEntityCell.class, "cell");
    	registerTileEntity(TileEntityBoatSling.class, "boat_sling");
    	registerTileEntity(TileEntityBarrel.class, "barrel");
    	registerTileEntity(TileEntityPirateChest.class, "pirate_chest");
    	registerTileEntity(TileEntityForge.class, "forge");
    	registerTileEntity(TileEntityCannon.class, "cannon");
    	registerTileEntity(TileEntityLight.class, "light_te");
    	registerTileEntity(TileEntityCleat.class, "cleat");
    	registerTileEntity(TileEntityPully.class, "pully");
    	registerTileEntity(TileEntityLiquorRack.class, "liquor_rack");
    	
    	proxy.preInit();
    	
    	GameRegistry.registerWorldGenerator(new WorldGenShips(), 0);
    	
    	CapabilityManager.INSTANCE.register(IDrunk.class, new DrunkStorage(), CapabilityDrunk::new);
    	
    	NETWORK.registerMessage(MessageSync.Handler.class, MessageSync.class, 0, Side.CLIENT);
    	NETWORK.registerMessage(MessageTESync.Handler.class, MessageTESync.class, 1, Side.SERVER);
    	
    	LootTableList.register(new ResourceLocation(MODID, "cursed_chest"));
    	
    	LootTableList.register(new ResourceLocation(MODID, "ghost_captain"));
    	//LootTableList.register(new ResourceLocation(MODID, "ghost_chest"));
    	
    	ModelRegistry.registerModel("dingy_mast");
    	ModelRegistry.registerModel("dingy_sail");
    	
    	try {
    		//EntityBoat.class.get
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
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
