package net.pirates.mod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public class ItemRope extends Item {
	
	public ItemRope() {
		super(PirateItemProperties.BASE_ONE);
	}

	public static Vec3d getPos(ItemStack stack) {
		if(stack.getTag() != null && stack.getTag().hasKey("x")) {
			NBTTagCompound tag = stack.getTag();
			return new Vec3d(tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z"));
		}
		return null;
	}
	
	public static void setRopePos(ItemStack stack, Vec3d pos) {
		NBTTagCompound tag = stack.getTag() == null ? new NBTTagCompound() : stack.getTag();
		tag.setDouble("x", pos.x);
		tag.setDouble("y", pos.y);
		tag.setDouble("z", pos.z);
		stack.setTag(tag);
	}

}
