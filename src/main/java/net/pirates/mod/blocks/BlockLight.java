package net.pirates.mod.blocks;

import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pirates.mod.tileentity.TileEntityLight;

public class BlockLight extends BlockAir {

	public BlockLight() {
		this.setLightLevel(1F);
		this.hasTileEntity = true;
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityLight();
	}
}
