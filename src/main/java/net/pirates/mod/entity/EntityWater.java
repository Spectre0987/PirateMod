package net.pirates.mod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWater extends EntityThrowable{

	public EntityWater(World worldIn) {
		super(worldIn);
	}
	public EntityWater(World worldIn, EntityLivingBase base) {
		super(worldIn, base);
		this.setPosition(base.posX, base.posY + base.getEyeHeight(), base.posZ);
		this.shoot(base, base.rotationPitch, base.rotationYawHead, 0, 1F, 1.5F);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.entityHit instanceof EntityLivingBase) {
			((EntityLivingBase)result.entityHit).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100));
			world.playSound(null, this.getPosition(), SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.HOSTILE, 1F, 1F);
		}
		this.setDead();
	}

}
