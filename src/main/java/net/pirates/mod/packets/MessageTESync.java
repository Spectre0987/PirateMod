package net.pirates.mod.packets;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.pirates.mod.tileentity.TileEntitySync;

public class MessageTESync implements IMessage{

	public BlockPos pos = BlockPos.ORIGIN;
	public UUID player;
	
	public MessageTESync() {}
	
	public MessageTESync(BlockPos pos, UUID playerID){
		this.pos = pos;
		this.player = playerID;
	}
	
	public MessageTESync(BlockPos pos){
		this.pos = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
		player = UUID.fromString(ByteBufUtils.readUTF8String(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		ByteBufUtils.writeUTF8String(buf, player.toString());
	}

	public static class Handler implements IMessageHandler<MessageTESync, IMessage>{

		@Override
		public IMessage onMessage(MessageTESync mes, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					WorldServer ws = ctx.getServerHandler().player.getServerWorld();
					if(!ws.isBlockLoaded(mes.pos)) return;
					EntityPlayerMP player = mes.player == null ? ctx.getServerHandler().player : (EntityPlayerMP)ws.getPlayerEntityByUUID(mes.player);
					TileEntitySync sync = (TileEntitySync)ws.getTileEntity(mes.pos);
					if(player != null && sync != null) {
						player.connection.sendPacket(sync.getUpdatePacket());
					}
				}});
			return null;
		}}
}
