package net.pirates.mod.client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pirates.mod.Pirate;

@Mod.EventBusSubscriber(modid = Pirate.MODID)
public class ModelRegistry {

	public static Item MODEL_ITEM = register(new Item(), "model_item");
	public static Map<String, ItemStack> modelNames = new HashMap<String, ItemStack>();
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		int id = 0;
		for(String name : modelNames.keySet()) {
			ModelLoader.setCustomModelResourceLocation(MODEL_ITEM, id, new ModelResourceLocation(Pirate.MODID + ":" + name, "inventory"));
			++id;
		}
	}
	
	@SubscribeEvent
	public static void registerModelItem(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(MODEL_ITEM);
	}
	
	public static Item register(Item item, String name) {
		item.setTranslationKey(Pirate.MODID + "." + name);
		item.setRegistryName(new ResourceLocation(Pirate.MODID, name));
		return item;
	}
	
	public static void registerModel(String name) {
		ModelRegistry.modelNames.put(name, new ItemStack(MODEL_ITEM, 1, modelNames.size()));
	}
	
	@SideOnly(Side.CLIENT)
	public static void renderItem(String name, TransformType type) {
		Minecraft.getMinecraft().getRenderItem().renderItem(modelNames.get(name), type);
	}
	
	public static class ItemModel extends Item{

		@Override
		public boolean getHasSubtypes() {
			return true;
		}

		@Override
		public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
			if(tab == null) {
				int id = 0;
				for(String name : modelNames.keySet()) {
					items.add(new ItemStack(MODEL_ITEM, 1, id++));
				}
			}
			super.getSubItems(tab, items);
		}
		
	}
}
