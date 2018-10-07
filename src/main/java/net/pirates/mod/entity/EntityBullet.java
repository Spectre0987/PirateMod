package net.pirates.mod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBullet extends EntityThrowable{

	public EntityBullet(World worldIn) {
		super(worldIn);
	}
	
	public EntityBullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.shoot(x, y, z, 2F, 0F);
	}
	
	public EntityBullet(World worldIn, EntityLivingBase base) {
		super(worldIn, base);
		this.shoot(base, base.rotationPitch, base.rotationYawHead, 0, 2F, 0F);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.typeOfHit == RayTraceResult.Type.BLOCK) {
			if(world.getBlockState(result.getBlockPos()).causesSuffocation()) {
				this.setDead();
			}
		}
		else if(result.entityHit != null) {
			result.entityHit.attackEntityFrom(DamageSource.GENERIC, 10F);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(world.isRemote) {
			for(int i = 0 ; i < 10; ++i) {
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX, posY, posZ, 0, 0, 0, 0);
			}
		}
	}

}
