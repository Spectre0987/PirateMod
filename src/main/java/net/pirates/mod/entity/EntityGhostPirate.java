package net.pirates.mod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.items.ItemFlintlock;
import net.pirates.mod.items.PItems;

public class EntityGhostPirate extends EntityMob implements IRangedAttackMob{

	public static final double SPEED = 0.5;
	public static DataParameter<Boolean> SHOOTING = EntityDataManager.createKey(EntityGhostPirate.class, DataSerializers.BOOLEAN);
	public static final AttributeModifier CAPTAIN_MOD = new AttributeModifier("Captain", 6D, 0);
	EntityAIAttackRanged ranged = new EntityAIAttackRanged(this, SPEED, 80, 30);
	EntityAIAttackMelee melee = new EntityAIAttackMelee(this, SPEED, false);
	private EnumPirateRank rank = EnumPirateRank.DECKHAND;
	private ItemStack specialDrop = ItemStack.EMPTY;
	
	public EntityGhostPirate(World worldIn) {
		super(worldIn);
		this.tasks.addTask(1, melee);
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
		this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 30));
		this.tasks.addTask(3, new EntityAIWander(this, SPEED));
		this.setPathPriority(PathNodeType.WATER, -1.0F);
		this.isImmuneToFire = true;
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(SPEED);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10D);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		if(this.getHeldItemMainhand().getItem() instanceof ItemFlintlock)
			compound.setBoolean("range", true);
		super.writeEntityToNBT(compound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		if(compound.hasKey("range") && compound.getBoolean("range")) {
			this.tasks.addTask(1, ranged);
			this.tasks.removeTask(melee);
		}
			
		super.readEntityFromNBT(compound);
	}
	
	public void setRank(EnumPirateRank rank) {
		this.rank = rank;
	}
	
	public EnumPirateRank getRank() {
		return this.rank;
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		this.genRandomGear();
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	public void genRandomGear() {
		double chance = this.rand.nextDouble();
		if(chance < 0.60)
			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(PItems.cutlass));
		else if(chance < 0.70) {
			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(PItems.flintlock));
			this.tasks.removeTask(melee);
			this.tasks.addTask(1, ranged);
		}
		else if(chance < 0.75) {
			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(PItems.bucket));
			this.tasks.removeTask(melee);
			this.tasks.addTask(1, ranged);
		}
		else
			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(PItems.dagger));
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(PItems.pirateHat));
		if(this.getRank() == EnumPirateRank.CAPTAIN) {
			this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Items.SHIELD));
			this.getEntityAttribute(SharedMonsterAttributes.ARMOR).applyModifier(CAPTAIN_MOD);
		}
	}
	
	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		if(!world.isRemote) {
			if(this.getHeldItemMainhand().getItem() instanceof ItemFlintlock) {
				EntityBullet ball = new EntityBullet(world, this);
				ball.setPosition(posX, posY + this.getEyeHeight(), posZ);
				world.spawnEntity(ball);
				world.playSound(null, this.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.MASTER, 1F, 1F);
			}
			else {
				EntityWater water = new EntityWater(world, this);
				world.spawnEntity(water);
			}
		}
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
		this.dataManager.set(SHOOTING, !swingingArms);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(SHOOTING, false);
	}
	
	public boolean isShooting() {
		return this.dataManager.get(SHOOTING);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(!world.isRemote) {
			if(this.getAttackTarget() != null) {
				if(this.getHeldItemMainhand().getItem() instanceof ItemFlintlock) {
					this.dataManager.set(SHOOTING, true);
				}
				else if(this.isShooting())
					this.dataManager.set(SHOOTING, false);
			}
		}
	}

	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		if(!world.isRemote && rand.nextDouble() < 0.1 + (0.1 * lootingModifier))
			InventoryHelper.spawnItemStack(world, posX, posY, posZ, new ItemStack(PItems.pirateHat));
			
	}

	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {}

	public static enum EnumPirateRank{
		CAPTAIN("ghosts/captain"),
		MATE("ghosts/mate"),
		DECKHAND("ghosts/hand");
		
		private ResourceLocation skin;
		
		EnumPirateRank(String skin) {
			this.skin = new ResourceLocation(Pirate.MODID, "textures/entity/" + skin);
		}
		
		public ResourceLocation getSkin() {
			return this.skin;
		}
	}

}
