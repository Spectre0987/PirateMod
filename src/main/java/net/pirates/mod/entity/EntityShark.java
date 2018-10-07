package net.pirates.mod.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityShark extends EntityMob{

	public EntityShark(World worldIn) {
		super(worldIn);
		this.setSize(1F, 0.5F);
	}

	@Override
	protected PathNavigate createNavigator(World worldIn) {
		return new PathNavigateSwimmer(this, worldIn);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		return super.attackEntityAsMob(entityIn);
	}
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {}

	@Override
	public boolean handleWaterMovement() {
		return false;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D, 100));
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0F, true));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 1.0F, 45));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, true));
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
		super.dropLoot(wasRecentlyHit, lootingModifier, source);
	}

}
