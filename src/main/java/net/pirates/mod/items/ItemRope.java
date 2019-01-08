package net.pirates.mod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.blocks.PBlocks;
import net.pirates.mod.helpers.Helper;
import net.pirates.mod.tileentity.TileEntityCleat;

public class ItemRope extends Item {
	
	public ItemRope() {
		this.setCreativeTab(Pirate.tab);
		this.setMaxStackSize(1);
	}

	public static Vec3d getPos(ItemStack stack) {
		if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("x")) {
			NBTTagCompound tag = stack.getTagCompound();
			return new Vec3d(tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z"));
		}
		return null;
	}
	
	public static void setRopePos(ItemStack stack, Vec3d pos) {
		NBTTagCompound tag = Helper.getTag(stack);
		tag.setDouble("x", pos.x);
		tag.setDouble("y", pos.y);
		tag.setDouble("z", pos.z);
		stack.setTagCompound(tag);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack held = player.getHeldItem(hand);
		if(worldIn.getBlockState(pos).getBlock() != PBlocks.cleat) {
			setRopePos(player.getHeldItem(hand), new Vec3d(pos.getX() + hitX, pos.getY() + hitY, pos.getZ() + hitZ));
		}
		else {
			TileEntityCleat cleat = (TileEntityCleat)worldIn.getTileEntity(pos);
			if(cleat != null && held.hasTagCompound() && held.getTagCompound().hasKey("x")) {
				cleat.addConnection(getPos(player.getHeldItem(hand)));
				player.getHeldItem(hand).setTagCompound(null);
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
