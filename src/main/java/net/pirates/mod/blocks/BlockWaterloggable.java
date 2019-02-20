package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;

public abstract class BlockWaterloggable extends Block{

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public BlockWaterloggable(Properties properties) {
		super(properties);
	}

	@Override
	protected void fillStateContainer(Builder<Block, IBlockState> builder) {
		builder.add(WATERLOGGED);
	}

}
