package net.pirates.mod.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;

public class ItemPirateCompass extends Item {
	
	public ItemPirateCompass() {
		this.setCreativeTab(Pirate.tab);
		this.setMaxStackSize(1);
		this.addPropertyOverride(new ResourceLocation(Pirate.MODID, "facing"), new IItemPropertyGetter() {
			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase e) {
				if(e == null)
					return 0F;
				if(e.getHorizontalFacing() == EnumFacing.EAST)
					return 0.1F;
				if(e.getHorizontalFacing() == EnumFacing.SOUTH)
					return 0.2F;
				if(e.getHorizontalFacing() == EnumFacing.WEST)
					return 0.3F;
				return 0F;
			}});
	}

	public static boolean inRange(float f, float min, float max) {
		return f < max && f > min;
	}
}
