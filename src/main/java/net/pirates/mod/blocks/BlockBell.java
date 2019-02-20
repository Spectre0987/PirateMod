package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pirates.mod.entity.EntityKraken;
import net.pirates.mod.misc.SoundRegistry;

public class BlockBell extends BlockWaterloggable {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final AxisAlignedBB BLOCK = new AxisAlignedBB(new BlockPos(1, 1, 1));
	
	public BlockBell() {
		super(PirateBlockProperties.BASE);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlayer().getHorizontalFacing().getOpposite());
	}

	@Override
	protected void fillStateContainer(Builder<Block, IBlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand,EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			for(EntityPlayerMP players : worldIn.getEntitiesWithinAABB(EntityPlayerMP.class, BLOCK.offset(pos).grow(40))) {
				worldIn.playSound(null, players.getPosition(), SoundRegistry.ship_bell, SoundCategory.BLOCKS, 1F, 1F);
			}
			
			for(EntityKraken kraken : worldIn.getEntitiesWithinAABB(EntityKraken.class, BLOCK.offset(pos).grow(64))) {
				kraken.getMoveHelper().setMoveTo(pos.getX() + 0.5, pos.getY(), pos.getZ(), 0.7);
			}
		}
		return true;
	}

}
