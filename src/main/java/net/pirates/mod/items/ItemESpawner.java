package net.pirates.mod.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.pirates.mod.Pirate;

public class ItemESpawner extends Item {

	EntityType<?> entity;
	
	public ItemESpawner(EntityType<?> e) {
		super(new Properties().group(Pirate.tab));
		this.entity = e;
	}

	@Override
	public EnumActionResult onItemUse(ItemUseContext con) {
		if(!con.getWorld().isRemote) {
			con.getItem().shrink(1);
			Entity e = entity.create(con.getWorld());
			e.setPosition(con.getPos().getX() + 0.5, con.getPos().getY() + 1, con.getPos().getZ() + 0.5);
			con.getWorld().spawnEntity(e);
			return EnumActionResult.SUCCESS;
		}
		return super.onItemUse(con);
	}
	
}
