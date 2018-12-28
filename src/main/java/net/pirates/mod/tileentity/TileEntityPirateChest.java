package net.pirates.mod.tileentity;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEntityPirateChest extends TileEntity implements IInventory{

	NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
	private ResourceLocation lootTable;
	
	@Override
	public String getName() {
		return "Cursed Chest";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return inv.size();
	}

	@Override
	public boolean isEmpty() {
		return inv.isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if(index >= inv.size())
			return ItemStack.EMPTY;
		return inv.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		this.markDirty();
		return this.getStackInSlot(index).splitStack(count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if(index >= inv.size())
			return ItemStack.EMPTY;
		ItemStack stack = this.getStackInSlot(index);
		inv.set(index, ItemStack.EMPTY);
		this.markDirty();
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if(index >= inv.size())
			return;
		this.markDirty();
		inv.set(index, stack);
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
		inv.clear();
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList list = compound.getTagList("inv", NBT.TAG_COMPOUND);
		int i = 0;
		for(NBTBase base : list) {
			inv.set(i, new ItemStack((NBTTagCompound)base));
			++i;
		}
		this.lootTable = compound.hasKey("loot") ? new ResourceLocation(compound.getString("loot")) : null;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		for(ItemStack stack : inv) {
			list.appendTag(stack.writeToNBT(new NBTTagCompound()));
		}
		compound.setTag("inv", list);
		if(this.lootTable != null)compound.setString("loot", this.lootTable.toString());
		return super.writeToNBT(compound);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("inventory.chest.pirate");
	}
	
	public void genLoot(EntityPlayer player) {
		if(!world.isRemote && this.lootTable != null) {
			LootTable table = world.getLootTableManager().getLootTableFromLocation(lootTable);
			table.fillInventory(this, new Random(), new LootContext.Builder((WorldServer)world).withPlayer(player).build());
			this.setLoot(null);
			System.out.println("Loot genned");
		}
	}
	
	public void setLoot(@Nullable ResourceLocation loc) {
		this.lootTable = loc;
		this.markDirty();
	}
	
	@Nullable
	public ResourceLocation getLoot() {
		return this.lootTable;
	}

}
