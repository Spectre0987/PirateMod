package net.pirates.mod.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class TileEntityBoatSling extends TileEntity implements ITickable{
	
	private NBTTagCompound boat = new NBTTagCompound();
	private int boatID = -1;
	
	public TileEntityBoatSling() {
		
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		boat = compound.getCompoundTag("boat");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("boat", boat);
		return super.writeToNBT(compound);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), -1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return boat;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		this.boat = pkt.getNbtCompound();
	}
	
	public void setBoat(@Nullable Entity e) {
		boat = new NBTTagCompound();
		this.markDirty();
		if(e == null) return;
		e.writeToNBT(boat);
		boat.setString("boatID", EntityList.getKey(e).toString());
		for(Entity pass : e.getPassengers()) {
			pass.dismountRidingEntity();
		}
		e.setDead();
	}
	
	@Nullable
	public Entity getBoat() {
		if(boat.hasNoTags()) return null;
		Entity e = EntityList.createEntityByIDFromName(new ResourceLocation(boat.getString("boatID")), world);
		e.readFromNBT(boat);
		e.setNoGravity(false);
		return e;
	}

	public void haulBoat(EntityBoat boat) {
		this.boatID = boat.getEntityId();
		boat.setNoGravity(true);
	}

	@Override
	public void update() {
		if(this.boatID != -1 && world.getEntityByID(boatID) instanceof EntityBoat) {
			EntityBoat boat = (EntityBoat)world.getEntityByID(boatID);
			Vec3d pos = new Vec3d(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());
			Vec3d motion = pos.subtract(boat.getPositionVector()).normalize().scale(0.15);
			boat.motionX += motion.x;
			boat.motionY += motion.y;
			boat.motionZ += motion.z;
			
			if(boat.getPositionVector().squareDistanceTo(pos) < Math.pow(1, 2)) {
				this.setBoat(boat);
				this.boatID = -1;
			}
		}
		if(!world.isRemote && world.getWorldTime() % 20 == 0) {
			for(EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(getPos()).grow(20))) {
				player.connection.sendPacket(this.getUpdatePacket());
			}
		}
	}

}
