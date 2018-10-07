package net.pirates.mod.misc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.items.ItemTelescope;
import net.pirates.mod.items.PItems;

public class RecipeTelescope implements IRecipe {

	public static ResourceLocation key;
	
	public RecipeTelescope() {
		this.setRegistryName(new ResourceLocation(Pirate.MODID, "telescope_upgrade"));
	}
	
	@Override
	public IRecipe setRegistryName(ResourceLocation name) {
		key = name;
		return this;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return key;
	}

	@Override
	public Class<IRecipe> getRegistryType() {
		return null;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		List list = new ArrayList<Item>();
		for(int i = 0; i < inv.getSizeInventory(); ++i) {
			list.add(inv.getStackInSlot(i).getItem());
		}
		return list.contains(PItems.telescope) && list.contains(Item.getItemFromBlock(Blocks.GLASS_PANE));
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack telescope = new ItemStack(PItems.telescope);
		int mag = 4;
		for(int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack tele = inv.getStackInSlot(i);
			if(tele.getItem() == PItems.telescope) {
				mag = ItemTelescope.getMagnification(tele);
				break;
			}
		}
		ItemTelescope.setMagnification(telescope, mag + 4);
		return telescope;
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height > 2;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(PItems.telescope);
	}

}
