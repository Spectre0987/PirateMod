package net.pirates.mod.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityCannonball extends EntityThrowable{

	public static final float SPEED = 2.0F;
	
	public EntityCannonball(World world) {
		super(world);
		this.setSize(1, 1);
	}
	
	public EntityCannonball(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.shoot(x, y, z, SPEED, rand.nextInt(1) - 0.5F);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.typeOfHit == RayTraceResult.Type.BLOCK) {
			IBlockState state = world.getBlockState(result.getBlockPos());
			if(state.isFullCube()) {
				world.createExplosion(this, posX, posY, posZ, 2, true);
				this.setDead();
			}
		}
		else {
			world.createExplosion(this, posX, posY, posZ, 2, true);
			this.setDead();
		}
	}

}
