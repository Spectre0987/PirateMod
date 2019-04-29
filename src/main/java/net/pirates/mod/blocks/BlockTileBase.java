package net.pirates.mod.blocks;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTileBase extends Block{

	Supplier<TileEntity> tile;

	public BlockTileBase(Material materialIn, Supplier<TileEntity> sup) {
		super(materialIn);
		this.tile = sup;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return tile.get();
	}
}
