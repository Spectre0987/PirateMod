package net.pirates.mod.capability;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.pirates.mod.Pirate;
import net.pirates.mod.misc.EnumWeaponType;
import net.pirates.mod.packets.MessageSync;

public class CapabilityDrunk implements IDrunk{

	private static Random rand = new Random();
	private static UUID profencincy = UUID.fromString("2a385745-0686-4e89-9ccb-1ff556228448");
	public Map<EnumWeaponType, Double> prof = new HashMap<EnumWeaponType, Double>();
	private int drunkTicks = 0;
	private boolean isDirty = true;
	
	@Override
	public int getDrunkTicks() {
		return this.drunkTicks;
	}

	@Override
	public void setDrunkTicks(int ticks) {
		this.drunkTicks = ticks;
		this.markDirty();
	}

	@Override
	public boolean isDirty() {
		return this.isDirty;
	}

	@Override
	public void markDirty() {
		this.isDirty = true;
	}

	@Override
	public void tickDrunk(EntityPlayer player) {
		if(this.getDrunkTicks() > 3000) {
			player.rotationYaw += player.world.getWorldTime() % 100 > 50 ? 0.25 : -0.25;
			player.rotationPitch += player.world.getWorldTime() % 100 > 50 ? 0.25 : -0.25;
			drunkTicks--;
			this.markDirty();
		}
		if(!player.world.isRemote && drunkTicks > 300 && rand.nextInt(600) == 0) {
			InventoryHelper.spawnItemStack(player.world, player.posX, player.posY, player.posZ, player.getHeldItemMainhand());
			player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
		}
	}
	
	public void readNBT(NBTTagCompound tag) {
		this.drunkTicks = tag.getInteger("drunkTicks");
		this.isDirty = tag.getBoolean("dirty");
	}
	
	public NBTTagCompound writeNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("drunkTicks", this.drunkTicks);
		tag.setBoolean("dirty", this.isDirty);
		return tag;
	}

	public void addDrunkTicks(int i) {
		this.drunkTicks += i;
		this.markDirty();
	}

	public void sync(EntityPlayer player) {
		Pirate.NETWORK.sendToAll(new MessageSync(player.getUniqueID(), this.writeNBT()));
	}
	
	public double getProficiency(EnumWeaponType type) {
		if(prof.containsKey(type))
			return prof.get(type);
		return 0.0;
	}
	
	public void setProfenciency(EnumWeaponType type, double amt) {
		prof.put(type, amt);
	}
}
