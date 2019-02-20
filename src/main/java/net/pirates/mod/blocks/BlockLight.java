package net.pirates.mod.blocks;

import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockLight extends BlockAir {

	public BlockLight() {
		super(Properties.create(Material.AIR).lightValue(15));
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
		return null; //return new TileEntityLight();
	}

}
