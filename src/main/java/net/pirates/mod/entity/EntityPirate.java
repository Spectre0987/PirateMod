package net.pirates.mod.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityPirate extends EntityMob{

	public static final double SPEED = 0.3;
	
	public EntityPirate(World worldIn) {
		super(worldIn);
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.3, false));
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
		this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 30));
		this.tasks.addTask(2, new EntityAIWander(this, SPEED));
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return pass == 1;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(SPEED);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

}
