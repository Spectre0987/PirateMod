package net.pirates.mod.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pirates.mod.entity.EntityBullet;

public class ItemFlintlock extends Item {
	
	public ItemFlintlock() {
		this.setMaxStackSize(1);
		this.setMaxStackSize(ToolMaterial.IRON.getMaxUses());
		this.setCreativeTab(CreativeTabs.COMBAT);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			EntityBullet b = new EntityBullet(worldIn, playerIn);
			Vec3d look = playerIn.getLookVec();
			b.setPosition(playerIn.posX + look.x, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ + look.z);
			worldIn.spawnEntity(b);
			worldIn.playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1F, 1F);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		// TODO Auto-generated method stub
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
