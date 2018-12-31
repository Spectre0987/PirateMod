package net.pirates.mod.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.pirates.mod.Pirate;
import net.pirates.mod.packets.MessageTESync;

/**Simple TE that syncs it's data on load**/
public abstract class TileEntitySync extends TileEntity{

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), -1, this.getUpdateTag());
	}

	@Override
	public abstract NBTTagCompound getUpdateTag();

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		this.sync(pkt.getNbtCompound());
	}

	public abstract void sync(NBTTagCompound tag);
	
	@Override
	public void onLoad() {
		super.onLoad();
		if(world.isRemote) {
			Pirate.NETWORK.sendToServer(new MessageTESync(this.getPos(), Minecraft.getMinecraft().player.getUniqueID()));
		}
	}

	@Override
	public void markDirty() {
		super.markDirty();
		if(world.isRemote) {
			Pirate.NETWORK.sendToServer(new MessageTESync(this.getPos(), Minecraft.getMinecraft().player.getUniqueID()));
		}
	}

}
