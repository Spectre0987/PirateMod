package net.pirates.mod.items;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.pirates.mod.IBoatSpawn;
import net.pirates.mod.Pirate;

public class ItemESpawner extends Item {

	IBoatSpawn entity;
	
	public ItemESpawner(IBoatSpawn e) {
		this.setCreativeTab(Pirate.tab);
		this.setMaxStackSize(1);
		this.entity = e;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		RayTraceResult res = worldIn.rayTraceBlocks(playerIn.getPositionVector().add(0, playerIn.getEyeHeight(), 0), playerIn.getPositionVector().add(playerIn.getLookVec().scale(6D)), true);
		if(res != null && res.typeOfHit == RayTraceResult.Type.BLOCK) {
			if(worldIn.getBlockState(res.getBlockPos()).getMaterial() == Material.WATER) {
				Entity e = entity.spawn(worldIn);
				e.setPosition(res.getBlockPos().getX() + 0.5, res.getBlockPos().getY() + 1, res.getBlockPos().getZ() + 0.5);
				worldIn.spawnEntity(e);
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
