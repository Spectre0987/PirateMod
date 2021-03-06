package net.pirates.mod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityGrappleHook extends EntityThrowable{

	
	public EntityGrappleHook(World worldIn, EntityLivingBase entity) {
		super(worldIn, entity);
		this.shoot(entity, entity.cameraPitch, entity.rotationYawHead, 0, 2, 0);
		this.thrower = entity;
	}
	
	public EntityGrappleHook(World world){
		super(world);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.typeOfHit == RayTraceResult.Type.BLOCK) {
			this.motionX = this.motionY = this.motionZ = 0.00D;
			this.setNoGravity(true);
		}
		else if(result.typeOfHit == RayTraceResult.Type.ENTITY && this.getThrower() != null) {
			result.entityHit.setPosition(this.getThrower().posX, this.getThrower().posY, this.getThrower().posZ);
			this.setDead();
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

}
