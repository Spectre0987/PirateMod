package net.pirates.mod.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.pirates.mod.items.PItems;

public class EntityPirateVillager extends EntityCreature{

	public EntityPirateVillager(World worldIn) {
		super(worldIn);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		super.writeEntityToNBT(compound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		super.readEntityFromNBT(compound);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		// TODO Auto-generated method stub
		return super.onInitialSpawn(difficulty, livingdata);
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) {
			this.genGear();
		}
		return super.processInteract(player, hand);
	}
	
	public void genGear() {
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(PItems.hammer));
		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(PItems.rum));
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(PItems.pirateHat));
	}
}
