package net.pirates.mod.tileentity;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.util.Constants;

public class TileEntityCleat extends TileEntitySync {

	List<Vec3d> connections = new ArrayList<Vec3d>();
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
		for(Vec3d pos : connections) {
			NBTTagCompound coord = new NBTTagCompound();
			coord.setDouble("x", pos.x);
			coord.setDouble("y", pos.y);
			coord.setDouble("z", pos.z);
			list.appendTag(coord);
		}
		tag.setTag("connections", list);
		return tag;
	}

	@Override
	public void sync(NBTTagCompound tag) {
		connections.clear();
		NBTTagList list = tag.getTagList("connections", Constants.NBT.TAG_COMPOUND);
		for(NBTBase base : list) {
			NBTTagCompound ct = (NBTTagCompound)base;
			Vec3d coord = new Vec3d(ct.getDouble("x"), ct.getDouble("y"), ct.getDouble("z"));
			connections.add(coord);
		}
	}

	public void addConnection(Vec3d pos) {
		connections.add(pos);
		this.markDirty();
	}

	public List<Vec3d> getConnections() {
		return this.connections;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList list = compound.getTagList("coords", Constants.NBT.TAG_COMPOUND);
		for(NBTBase base : list) {
			NBTTagCompound tag = (NBTTagCompound)base;
			connections.add(new Vec3d(tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z")));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		for(Vec3d vec : this.connections) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setDouble("x", vec.x);
			tag.setDouble("y", vec.y);
			tag.setDouble("z", vec.z);
			list.appendTag(tag);
		}
		compound.setTag("coords", list);
		return super.writeToNBT(compound);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return Block.FULL_BLOCK_AABB.grow(20D).offset(this.getPos());
	}
	
	
}
