package net.pirates.mod;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pirates.mod.items.PItems;

@Mod(Pirate.MODID)
public class Pirate
{
    public static final String MODID = "pirates";
    
    public static Pirate instance;
    
    public static ItemGroup tab = new ItemGroup(MODID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(PItems.sextant);
		}};;
    
    public Pirate() {
    	instance = this;
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
    }
    
    public void preInit(FMLCommonSetupEvent event){
    	
    	LootTableList.register(new ResourceLocation(MODID, "cursed_chest"));
    	LootTableList.register(new ResourceLocation(MODID, "ghost_captain"));
    }
}
