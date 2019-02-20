package net.pirates.mod;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pirates.mod.client.ModelRegistry;
import net.pirates.mod.entity.EntityBottle;
import net.pirates.mod.entity.EntityBullet;
import net.pirates.mod.entity.EntityCannonball;
import net.pirates.mod.entity.EntityDingy;
import net.pirates.mod.entity.EntityGrappleHook;
import net.pirates.mod.entity.EntityKraken;
import net.pirates.mod.entity.EntityMermaid;
import net.pirates.mod.entity.EntityPirate;
import net.pirates.mod.entity.EntityPirateHuman;
import net.pirates.mod.entity.EntityPulledBlock;
import net.pirates.mod.entity.EntityShark;
import net.pirates.mod.handlers.EntityHelper;
import net.pirates.mod.items.PItems;
import net.pirates.mod.tileentity.TileEntityBarrel;
import net.pirates.mod.tileentity.TileEntityBoatSling;
import net.pirates.mod.tileentity.TileEntityCannon;
import net.pirates.mod.tileentity.TileEntityCell;
import net.pirates.mod.tileentity.TileEntityCleat;
import net.pirates.mod.tileentity.TileEntityForge;
import net.pirates.mod.tileentity.TileEntityLight;
import net.pirates.mod.tileentity.TileEntityPirateChest;
import net.pirates.mod.tileentity.TileEntityPully;

@Mod(Pirate.MODID)
public class Pirate
{
    public static final String MODID = "pirates";
    
    public static Pirate instance;
    
    public static ItemGroup tab;
    
    public Pirate() {
    	instance = this;
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
    }
    
    public void preInit(FMLCommonSetupEvent event)
    {	
    	tab = new ItemGroup(MODID) {
			@Override
			public ItemStack createIcon() {
				return new ItemStack(PItems.sextant);
			}};
    	EntityHelper.registerProjectiles(EntityBullet.class, "bullet");
    	EntityHelper.registerProjectiles(EntityCannonball.class, "cannon_ball");
    	EntityHelper.registerProjectiles(EntityBottle.class, "bottle");
    	EntityHelper.registerMobs(EntityPirate.class, "pirate");
    	EntityHelper.registerProjectiles(EntityGrappleHook.class, "grapple_hook");
    	EntityHelper.registerWaterMobs(EntityShark.class, "shark");
    	EntityHelper.registerMobs(EntityPirateHuman.class, "pirate_human");
    	EntityHelper.registerWaterMobs(EntityKraken.class, "kraken");
    	EntityHelper.registerWaterMobs(EntityMermaid.class, "mermaid");
    	EntityHelper.registerWaterMobs(EntityDingy.class, "dingy");
    	EntityHelper.registerProjectiles(EntityPulledBlock.class, "pulled_block");
    	
    	registerTileEntity(TileEntityCell.class, "cell");
    	registerTileEntity(TileEntityBoatSling.class, "boat_sling");
    	registerTileEntity(TileEntityBarrel.class, "barrel");
    	registerTileEntity(TileEntityPirateChest.class, "pirate_chest");
    	registerTileEntity(TileEntityForge.class, "forge");
    	registerTileEntity(TileEntityCannon.class, "cannon");
    	registerTileEntity(TileEntityLight.class, "light_te");
    	registerTileEntity(TileEntityCleat.class, "cleat");
    	registerTileEntity(TileEntityPully.class, "pully");
    	
    	//GameRegistry.registerWorldGenerator(new WorldGenShips(), 0);
    	
    	//CapabilityManager.INSTANCE.register(IDrunk.class, new DrunkStorage(), CapabilityDrunk::new);
    	
    	//NETWORK.registerMessage(MessageSync.Handler.class, MessageSync.class, 0, Side.CLIENT);
    	//NETWORK.registerMessage(MessageTESync.Handler.class, MessageTESync.class, 1, Side.SERVER);
    	
    	LootTableList.register(new ResourceLocation(MODID, "cursed_chest"));
    	
    	LootTableList.register(new ResourceLocation(MODID, "ghost_captain"));
    	
    	ModelRegistry.registerModel("dingy_mast");
    	ModelRegistry.registerModel("dingy_sail");
    	

    }
	
	private static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
		//GameRegistry.registerTileEntity(clazz, new ResourceLocation(Pirate.MODID, name));
	}
}
