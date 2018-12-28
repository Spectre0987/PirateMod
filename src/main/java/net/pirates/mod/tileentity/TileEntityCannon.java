package net.pirates.mod.tileentity;

import java.util.Random;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.pirates.mod.blocks.PBlocks;
import net.pirates.mod.entity.EntityCannonball;
import net.pirates.mod.helpers.Helper;

public class TileEntityCannon extends TileEntity{
	
	private int powder = 0;
	private boolean rammed = false;
	private boolean hasBall = false;
	private static int MAX_BASE_POWDER = 3;
	private static Random rand = new Random();
	
	public void setGunpowder(int powder) {
		this.powder = powder;
		this.markDirty();
	}
	
	public void setHasBall(boolean ball) {
		this.hasBall = ball;
		this.markDirty();
	}
	
	public void setRammed(boolean isRammed) {
		this.rammed = isRammed;
		this.markDirty();
	}
	
	public int getGunpowder() {
		return this.powder;
	}
	
	public boolean isRammed() {
		return this.rammed;
	}
	
	public boolean hasBall() {
		return this.hasBall;
	}

	public boolean fire() {
		if(this.hasBall() && this.getGunpowder() > 0) {
			if(!world.isRemote) {
				if(explodes()) return true;
				if(world == null || world.getBlockState(this.getPos()).getBlock() != PBlocks.cannon) return false;
				EnumFacing face = world.getBlockState(this.getPos()).getValue(BlockHorizontal.FACING);
				if(face != null) {
					Vec3d pos = Helper.getVecFromFace(face).scale(1D);
					EntityCannonball ball = new EntityCannonball(world, pos.x, pos.y, pos.z, this.getGunpowder());
					ball.setPosition(this.getPos().getX() + pos.x, this.getPos().getY() + 0.25, pos.z + this.getPos().getZ());
					world.spawnEntity(ball);
					this.setHasBall(false);
					this.setGunpowder(0);
					this.setRammed(false);
				}
			}
			return true;
		}
		return false;
	}
	
	private boolean explodes() {
		if(this.getGunpowder() > MAX_BASE_POWDER + rand.nextInt(2)) {
			world.createExplosion(null, this.getPos().getX() + 0.5, this.getPos().getY(), this.getPos().getZ() + 0.5, 3, true);
			return true;
		}
		else return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.hasBall = compound.getBoolean("ball");
		this.rammed = compound.getBoolean("ram");
		this.powder = compound.getInteger("powder");
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("ball", this.hasBall);
		compound.setBoolean("ram", this.rammed);
		compound.setInteger("powder", this.powder);
		return super.writeToNBT(compound);
	}
}
