package net.pirates.mod.entity;

import net.minecraft.entity.item.EntityBoat;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityDingy extends EntityBoat{

	public EntityDingy(World worldIn) {
		super(worldIn);
	}

	@Override
	public double getMountedYOffset() {
		return 0.5D;
	}

	@Override
	public Item getItemBoat() {
		return super.getItemBoat();
	}

}
