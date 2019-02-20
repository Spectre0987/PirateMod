package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;

public class BlockRailing extends BlockHorizontal {

	public static final BooleanProperty END = BooleanProperty.create("end");
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public BlockRailing() {
		super(Properties.create(Material.WOOD).hardnessAndResistance(3F));
		this.setDefaultState(this.getDefaultState().with(FACING, EnumFacing.NORTH));
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlayer().getHorizontalFacing());
	}

	@Override
	protected void fillStateContainer(Builder<Block, IBlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(END);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}
