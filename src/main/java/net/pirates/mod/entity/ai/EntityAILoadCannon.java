package net.pirates.mod.entity.ai;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.pirates.mod.blocks.BlockCannon;
import net.pirates.mod.entity.EntityGhostPirate;

public class EntityAILoadCannon extends EntityAIBase{

	private static BlockPos RADIUS = new BlockPos(5, 5, 5);
	private EntityGhostPirate pirate;
	private BlockPos cannonPos = BlockPos.ORIGIN;
	
	public EntityAILoadCannon(EntityGhostPirate pirate) {
		this.pirate = pirate;
		this.setMutexBits(1);
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return this.shouldExecute();
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
	}

	@Override
	public void updateTask() {
		pirate.getMoveHelper().setMoveTo(cannonPos.getX(), cannonPos.getY(), cannonPos.getZ(), pirate.getAIMoveSpeed());
	}

	@Override
	public boolean shouldExecute() {
		return getCannonPos(pirate) != BlockPos.ORIGIN;
	}
	
	public BlockPos getCannonPos(EntityGhostPirate pirate) {
		for(BlockPos cpos : BlockPos.getAllInBox(pirate.getPosition().subtract(RADIUS), pirate.getPosition().add(RADIUS))) {
			IBlockState state = pirate.getEntityWorld().getBlockState(cpos);
			if(state.getBlock() instanceof BlockCannon) {
				this.cannonPos = cpos;
				return cpos;
			}
		}
		return BlockPos.ORIGIN;
	}

}
