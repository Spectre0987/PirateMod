package net.pirates.mod.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pirates.mod.entity.water.ai.EntityMoveHandlerWater;

public class EntityKraken extends EntityWaterMob{

	public static final AxisAlignedBB BREAK_BB = new AxisAlignedBB(-4, 1, -4, 4, 4, 4);
	
	public EntityKraken(World worldIn) {
		super(worldIn);
		this.setSize(2F, 6F);
		this.moveHelper = new EntityMoveHandlerWater(this);
		this.tasks.addTask(1, new EntityMermaid.EntityAIWanderSwim(this, 0.7));
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		this.breakBlocks();
	}
	
	public void breakBlocks() {
		for(BlockPos pos : getBlockPosInAABB(world, this.getEntityBoundingBox().grow(1.5))) {
			IBlockState state = world.getBlockState(pos);
			if(state.getMaterial() != Material.AIR && state.getMaterial() != Material.WATER && state.isFullBlock()) {
				EntityFallingBlock entity = new EntityFallingBlock(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, state);
				world.setBlockToAir(pos);
				entity.motionX = -1 + (rand.nextInt(200) / 100);
				entity.motionZ = -1 + (rand.nextInt(200) / 100);
				entity.motionY += 1;
				world.spawnEntity(entity);
			}
		}
	}
	
	public static List<BlockPos> getBlockPosInAABB(World world, AxisAlignedBB bb){
		List<BlockPos> list = new ArrayList<BlockPos>();
		for(int x = (int) bb.minX; x < bb.maxX; ++x) {
			for(int y = (int) bb.minY; y < bb.maxY; ++y) {
				for(int z = (int) bb.minZ; z < bb.maxZ; ++z) {
					list.add(new BlockPos(x, y, z));
				}
			}
		}
		return list;
	}

}
