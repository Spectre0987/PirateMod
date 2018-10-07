package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.pirates.mod.Pirate;

public class BlockBase extends Block{
	
	public BlockBase(Material mat) {
		super(mat);
		this.setCreativeTab(Pirate.tab);
	}

}
