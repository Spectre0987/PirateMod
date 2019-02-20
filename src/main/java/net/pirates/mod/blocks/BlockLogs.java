package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;

public class BlockLogs extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public BlockLogs() {
		super(PirateBlockProperties.BASE.hardnessAndResistance(3F));
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getFace().getOpposite());
	}

	@Override
	protected void fillStateContainer(Builder<Block, IBlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return state.getBlock() != PBlocks.ghastly_log;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

}
