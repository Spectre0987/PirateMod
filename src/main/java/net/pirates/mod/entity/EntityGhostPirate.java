package net.pirates.mod.entity;

import net.minecraft.block.material.Material;
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
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
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
	public static DataParameter<Integer> SKIN_INDEX = EntityDataManager.createKey(EntityGhostPirate.class, DataSerializers.VARINT);
	public static DataParameter<String> RANK = EntityDataManager.createKey(EntityGhostPirate.class, DataSerializers.STRING);
	public static final AttributeModifier CAPTAIN_MOD = new AttributeModifier("Captain", 6D, 0);
	EntityAIAttackRanged ranged = new EntityAIAttackRanged(this, SPEED, 80, 30);
	EntityAIAttackMelee melee = new EntityAIAttackMelee(this, SPEED, false);
	public int animationTicks = 0;

	private ItemStack specialDrop = ItemStack.EMPTY;
	private int dustRotation = 0;
	
	public EntityGhostPirate(World worldIn) {
		super(worldIn);
		this.tasks.addTask(1, melee);
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
		this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 30));
		this.tasks.addTask(3, new EntityAIWander(this, SPEED));
		this.setPathPriority(PathNodeType.WATER, -1.0F);
		this.isImmuneToFire = true;
		this.setRank(EnumPirateRank.DECKHAND);
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return pass == 0;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(SPEED);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10D);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		Item held = this.getHeldItemMainhand().getItem();
		if(held instanceof ItemFlintlock || held instanceof ItemBucket)
			compound.setBoolean("range", true);
		compound.setString("rank", this.getRank().name());
		compound.setInteger("skin_index", this.dataManager.get(SKIN_INDEX));
		super.writeEntityToNBT(compound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if(compound.hasKey("range") && compound.getBoolean("range")) {
			this.tasks.addTask(1, ranged);
			this.tasks.removeTask(melee);
		}
		if(compound.hasKey("rank") && !compound.getString("rank").isEmpty())
			this.setRank(EnumPirateRank.valueOf(compound.getString("rank")));
		this.dataManager.set(SKIN_INDEX, compound.getInteger("skin_index"));
	}
	
	public void setRank(EnumPirateRank rank) {
		if(!world.isRemote) {
			int skin = this.world.rand.nextInt(rank.getSkins().length);
			this.dataManager.set(SKIN_INDEX, skin);
			this.dataManager.set(RANK, rank.name());
		}
	}
	
	public EnumPirateRank getRank() {
		return EnumPirateRank.valueOf(this.dataManager.get(RANK));
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
		if(rand.nextDouble() < 0.25)
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(PItems.pirateHat));
		if(this.getRank() == EnumPirateRank.CAPTAIN) {
			this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Items.SHIELD));
			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(rand.nextBoolean() ? PItems.captain_cutlass : PItems.rapier));
			this.getEntityAttribute(SharedMonsterAttributes.ARMOR).applyModifier(CAPTAIN_MOD);
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(rand.nextDouble() < 0.5 ? PItems.barbossaHat : PItems.pirateHat));
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
				this.animationTicks = 60;
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
		this.dataManager.register(SKIN_INDEX, 0);
		this.dataManager.register(RANK, EnumPirateRank.DECKHAND.name());
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
		if(this.animationTicks > 0)
			--this.animationTicks;
		
		if(this.isInsideOfMaterial(Material.WATER)) {
			if(!world.isRemote) {
				this.attackEntityFrom(DamageSource.DROWN, 1F);
			}
			else {
				for(int i = 0; i < 10; ++i) {
					world.spawnParticle(EnumParticleTypes.CLOUD, posX + Math.sin(this.dustRotation), posY, posZ + Math.cos(this.dustRotation), 0, 0.2, 0, 0xFFFFFF);
					this.dustRotation = (this.dustRotation + 20) % 360;
				}
			}
		}
	}

	public int getSkinIndex() {
		return this.dataManager.get(SKIN_INDEX);
	}
	
	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		if(!world.isRemote) {
			if(rand.nextDouble() < 0.5 + (0.1 * lootingModifier) && this.getHeldItemMainhand().getItem() != PItems.flintlock)
				InventoryHelper.spawnItemStack(world, posX, posY, posZ, new ItemStack(this.getHeldItemMainhand().getItem()));
				InventoryHelper.spawnItemStack(world, posX, posY, posZ, new ItemStack(this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem()));
		}
			
	}

	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {}

	public static enum EnumPirateRank{
		CAPTAIN(
			"pirate_midship_002",
			"pirate_midship_003"),
		MATE(
			"pirate_braced_002",
			"pirate_braced_003",
			"pirate_deckhand_004",
			"pirate_midship_001",
			"pirate_midship_004"),
		DECKHAND(
			"pirate_braced_001",
			"pirate_braced_004",
			"pirate_deckhand_001",
			"pirate_deckhand_002",
			"pirate_deckhand_003");
		
		private ResourceLocation[] skin;
		
		EnumPirateRank(String... skin) {
			int index = 0;
			this.skin = new ResourceLocation[skin.length];
			for(String name : skin) {
				this.skin[index] = new ResourceLocation(Pirate.MODID, "textures/entity/pirates/" + name + ".png");
				++index;
			}
		}
		
		public ResourceLocation[] getSkins() {
			return this.skin;
		}
		
		public ResourceLocation getSkin(int index){
			return this.skin[index];
		}
	}
}
