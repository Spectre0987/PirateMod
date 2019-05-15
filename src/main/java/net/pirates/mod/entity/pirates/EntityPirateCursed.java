package net.pirates.mod.entity.pirates;

import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.pirates.mod.items.PItems;

public class EntityPirateCursed extends EntityPirate{

	public static final DataParameter<Boolean> SKELETON = EntityDataManager.createKey(EntityPirateCursed.class, DataSerializers.BOOLEAN);
	EntityAIAttackMelee melee = new EntityAIAttackMelee(this, 0.5, false);
	
	public EntityPirateCursed(World worldIn) {
		super(worldIn);
		this.tasks.addTask(1, melee);
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
		this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 30));
		this.tasks.addTask(3, new EntityAIWander(this, 0.5));
		this.setPathPriority(PathNodeType.DAMAGE_FIRE, 1.0F);
		this.isImmuneToFire = true;
		this.setRank(EnumPirateRank.DECKHAND);
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(PItems.cutlass));
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(PItems.pirateHat));
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(SKELETON, false);
	}

	public boolean isSkeleton() {
		return this.dataManager.get(SKELETON);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		if(!world.isRemote) {
			if(this.isSkeleton()) {
				if(world.canBlockSeeSky(this.getPosition().down()))
					this.dataManager.set(SKELETON, false);
			}
		}
	}

}
