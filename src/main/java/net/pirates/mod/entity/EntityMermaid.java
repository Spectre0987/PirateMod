package net.pirates.mod.entity;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityMermaid extends EntityWaterMob{

	public EntityMermaid(World worldIn) {
		super(worldIn);
		this.setSize(0.85F, 1F);
		this.tasks.addTask(0, new EntityAISavePlayer(this, 20));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 32F));
		this.tasks.addTask(1, new EntityAIWanderSwim(this, 0.3));
		this.stepHeight = 0.95F;
	}
	
	public static class EntityAISavePlayer extends EntityAIBase{

		EntityMermaid mer;
		EntityPlayer target;
		double range = 0;
		
		public EntityAISavePlayer(EntityMermaid entity, double range) {
			this.mer = entity;
			this.range = range;
			this.setMutexBits(1);
		}
		
		@Override
		public boolean shouldExecute() {
			return mer != null && mer.world.getClosestPlayerToEntity(mer, range) != null;
		}

		@Override
		public void startExecuting() {
			target = mer.world.getClosestPlayerToEntity(mer, range);
		}

		@Override
		public void updateTask() {
			if(target != null && target.getAir() < 1 && mer.isInsideOfMaterial(Material.WATER)) {
				if(target.getPositionVector().distanceTo(mer.getPositionVector()) > 3) {
					Vec3d dir = target.getPositionVector().subtract(mer.getPositionVector()).scale(0.05);
					mer.move(MoverType.SELF, dir.x, dir.y, dir.z);
					mer.faceEntity(target, 5, 5);
				}
				else {
					mer.move(MoverType.SELF, 0, 0.5, 0);
					if(!mer.world.isRemote) {
						((EntityPlayerMP)target).connection.setPlayerLocation(mer.posX, mer.posY, mer.posZ, target.rotationYaw, 0);
					}
				}
			}
		}
		
	}
	
	@Override
	public PathNavigate createNavigator(World worldIn)
    {
        return new PathNavigateSwimmer(this, worldIn);
    }
	
	public static class EntityAIWanderSwim extends EntityAIBase{
		
		public EntityWaterMob mob;
		public double speed;
		public long time = -1;
		Random rand = new Random();
		
		
		public EntityAIWanderSwim(EntityWaterMob mob, double speed) {
			this.mob = mob;
			this.speed = speed;
			this.setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void updateTask() {
			if(time == -1 || mob.world.getWorldTime() > time) {
				time = mob.world.getWorldTime() + rand.nextInt(90);
			}
			mob.moveForward += 0.2;
		}
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(this.isInsideOfMaterial(Material.WATER)) {
			this.setNoGravity(true);
		}
		else this.setNoGravity(false);
	}
}
