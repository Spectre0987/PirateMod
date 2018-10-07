package net.pirates.mod.capability;

import net.minecraft.entity.player.EntityPlayer;

public interface IDrunk {
	
	int getDrunkTicks();
	void setDrunkTicks(int ticks);
	boolean isDirty();
	void markDirty();
	public void tickDrunk(EntityPlayer player);

}
