package net.pirates.mod.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBottle extends Entity{

	private double speedX = 0.5D;
	private double speedZ = 0.5D;
	
	public EntityBottle(World worldIn) {
		super(worldIn);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		this.handleMove();
		this.motionX += this.speedX;
		this.motionZ += this.speedZ;
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}
	
	public void handleMove() {
		if(!this.isInsideOfMaterial(Material.WATER)) {
			this.motionY -= 0.23D;
		}
		this.setPosition(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;
	}

}
