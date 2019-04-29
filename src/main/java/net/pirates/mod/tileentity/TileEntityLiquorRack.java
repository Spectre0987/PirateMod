package net.pirates.mod.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEntityLiquorRack extends TileEntity implements IInventory{

	private NonNullList<ItemStack> INVENTORY = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	private long time = 0;
	
	@Override
	public String getName() {
		return "Liquor Rack";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return INVENTORY.size();
	}

	@Override
	public boolean isEmpty() {
		return INVENTORY.isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return index > this.getSizeInventory() ? ItemStack.EMPTY : INVENTORY.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return this.getStackInSlot(index).splitStack(count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack old = this.getStackInSlot(index);
		this.setInventorySlotContents(index, ItemStack.EMPTY);
		this.markDirty();
		return old;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if(index < INVENTORY.size()) {
			INVENTORY.set(index, stack);
			this.markDirty();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		INVENTORY.clear();
		this.markDirty();
	}
	
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), -1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.serializeNBT();
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		this.deserializeNBT(pkt.getNbtCompound());
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList inv = compound.getTagList("inv", NBT.TAG_COMPOUND);
		int id = 0;
		for(NBTBase base : inv) {
			INVENTORY.set(id, new ItemStack((NBTTagCompound)base));
			++id;
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		for(ItemStack stack : INVENTORY) {
			list.appendTag(stack.serializeNBT());
		}
		compound.setTag("inv", list);
		return super.writeToNBT(compound);
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}

	public void update() {
		if(!world.isRemote)
			world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
	}

	public boolean addBottle(ItemStack held) {
		if(time > world.getTotalWorldTime() + 20) return false;
		int index = 0;
		for(ItemStack stack : INVENTORY) {
			if(stack.isEmpty()) {
				this.setInventorySlotContents(index, held.copy());
				this.update();
				return true;
			}
			++index;
		}
		time = world.getTotalWorldTime();
		return false;
	}

	public void removeBottle(EntityPlayer playerIn) {
		if(time > world.getTotalWorldTime() + 20) return;
		if(!world.isRemote) {
			for(ItemStack item : INVENTORY) {
				if(!item.isEmpty()) {
					InventoryHelper.spawnItemStack(world, playerIn.posX, playerIn.posY, playerIn.posZ, item);
					this.update();
					return;
				}
			}
		}
		time = world.getTotalWorldTime();
	}

}
