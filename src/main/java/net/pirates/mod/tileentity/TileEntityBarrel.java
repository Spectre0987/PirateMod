package net.pirates.mod.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;

public class TileEntityBarrel extends TileEntity implements ITickable{

	private int waterCount = 0;
	private int sugarCount = 0;
	private int rumAmount = 0;
	private int ticks = 0;
	
	public TileEntityBarrel() {}
	
	@Override
	public void update() {
		if(this.waterCount > 0 && sugarCount > 5) {
			++ticks;
			if(ticks > 200) {
				this.waterCount -= 1;
				this.sugarCount -= 5;
				this.rumAmount += 5;
				this.ticks = 0;
				this.markDirty();
			}
			if(world.isRemote && world.getWorldTime() % 20 == 0) {
				world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.getPos().getX() + 0.5, this.getPos().getY() + 1.1, this.getPos().getZ() + 0.5, 0, 1, 0, 0);
			}
		}
		if(!world.isRemote && world.getWorldTime() % 20 == 0) {
			for(EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(20))) {
				player.connection.sendPacket(this.getUpdatePacket());
			}
		}
	}
	
	public int getRumAmount() {
		return this.rumAmount;	
	}
	
	public int getSugarCount() {
		return this.sugarCount;
	}
	
	public int getWater() {
		return this.waterCount;
	}
	
	public void setRumAmount(int i) {
		this.rumAmount = i;
		this.markDirty();
	}
	
	public void setSugarAmount(int i) {
		this.sugarCount = i;
		this.markDirty();
	}
	
	public void setWater(int b) {
		this.waterCount = b;
		this.markDirty();
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), -1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.waterCount = compound.getInteger("water");
		this.rumAmount = compound.getInteger("rum");
		this.sugarCount = compound.getInteger("sugar");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("water", this.waterCount);
		compound.setInteger("rum", this.rumAmount);
		compound.setInteger("sugar", this.sugarCount);
		return super.writeToNBT(compound);
	}

}
