package net.pirates.mod.entity.pirates;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.entity.EntityGhostPirate;
import net.pirates.mod.entity.pirates.EntityPirate.EnumPirateRank;

public abstract class EntityPirate extends EntityMob{

	public static DataParameter<Integer> SKIN_INDEX = EntityDataManager.createKey(EntityPirate.class, DataSerializers.VARINT);
	public static DataParameter<String> RANK = EntityDataManager.createKey(EntityPirate.class, DataSerializers.STRING);
	
	public EntityPirate(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(SKIN_INDEX, 0);
		this.dataManager.register(RANK, EnumPirateRank.DECKHAND.name());
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
	
	public int getSkinIndex() {
		return this.dataManager.get(SKIN_INDEX);
	}

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
