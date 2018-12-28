package net.pirates.mod.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;

public class TileEntityCell extends TileEntity implements ITickable{

	private NBTTagCompound entity = new NBTTagCompound();
	
	public TileEntityCell() {
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		entity = compound.getCompoundTag("entity");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("entity", entity);
		return super.writeToNBT(compound);
	}

	@Override
	public void invalidate() {
		super.invalidate();
	}
	
	public NBTTagCompound getEntityTag() {
		return this.entity;
	}
	
	@Nullable
	public Entity getEntity() {
		if(entity.isEmpty()) return null;
		Entity e = EntityList.createEntityByIDFromName(new ResourceLocation(entity.getString("entityID")), world);
		e.readFromNBT(entity);
		return e;
	}
	
	public void setEntity(@Nullable Entity e) {
		this.markDirty();
		this.entity = new NBTTagCompound();
		if(e == null)
			return;
		this.entity = new NBTTagCompound();
		e.writeToNBT(entity);
		entity.setString("entityID", EntityList.getKey(e).toString());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), -1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.entity;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		this.entity = pkt.getNbtCompound();
	}

	@Override
	public void update() {
		if(!world.isRemote && world.getWorldTime() % 20 == 0) {
			for(EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(20D))) {
				player.connection.sendPacket(this.getUpdatePacket());
			}
		}
	}

}
