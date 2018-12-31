package net.pirates.mod.tileentity;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.pirates.mod.blocks.PBlocks;
import net.pirates.mod.items.ItemLantern;
import net.pirates.mod.items.PItems;

public class TileEntityLight extends TileEntity implements ITickable{

	UUID lanternID;
	UUID playerID;
	
	public TileEntityLight() {}
	
	@Override
	public void update() {
		if(!world.isRemote) {
			if(playerID == null) {
				world.setBlockToAir(getPos());
				return;
			}
			EntityPlayer player = ((WorldServer)world).getPlayerEntityByUUID(playerID);
			if(player == null) {
				world.setBlockToAir(getPos());
				return;
			}
			ItemStack held = player.getHeldItemMainhand().getItem() == PItems.lantern ? player.getHeldItemMainhand() : player.getHeldItemOffhand();
			if(held.getItem() != PItems.lantern || !ItemLantern.isOn(held)){
				world.setBlockToAir(getPos());
				return;
			}
			BlockPos lPos = player.getPosition().up();
			if(!lPos.equals(this.getPos()) && world.isAirBlock(lPos)) {
				world.setBlockState(lPos, PBlocks.light_te.getDefaultState(), 2);
				TileEntityLight light = (TileEntityLight) world.getTileEntity(lPos);
				if(light != null) {
					light.setPlayerID(this.getPlayerID());
				}
				world.setBlockToAir(getPos());
				world.checkLight(lPos);
				return;
			}
		}
	}
	
	public void setUUID(UUID id) {
		this.lanternID = id;
	}
	
	public UUID getLanternID() {
		return this.lanternID;	
	}

	public void setPlayerID(UUID id) {
		this.playerID = id;
	}
	
	public UUID getPlayerID() {
		return this.playerID;
	}
}
