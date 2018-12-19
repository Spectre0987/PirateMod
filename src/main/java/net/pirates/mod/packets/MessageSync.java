package net.pirates.mod.packets;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.pirates.mod.capability.CapabilityDrunk;
import net.pirates.mod.capability.DrunkStorage;

public class MessageSync implements IMessage{

	NBTTagCompound tag;
	UUID playerID;
	
	public MessageSync() {}
	
	public MessageSync(UUID id, NBTTagCompound tag) {
		this.tag = tag;
		this.playerID = id;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) {
		tag = ByteBufUtils.readTag(buf);
		playerID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, tag);
		ByteBufUtils.writeUTF8String(buf, playerID.toString());
	}
	
	public static class Handler implements IMessageHandler<MessageSync, IMessage>{

		@Override
		public IMessage onMessage(MessageSync message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					if(Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().world.getPlayerEntityByUUID(message.playerID) == null) return;
					CapabilityDrunk drunk = Minecraft.getMinecraft().world.getPlayerEntityByUUID(message.playerID).getCapability(DrunkStorage.DRUNK, null);
					if(drunk != null) {
						drunk.readNBT(message.tag);
					}
				}
			});
			return null;
		}}

}
