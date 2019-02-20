package net.pirates.mod.items;

import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.pirates.mod.Pirate;
import net.pirates.mod.client.models.ModelSash;

public class ItemSash extends ItemArmor {

	public static final IArmorMaterial PIRATE_MATERIAL = ArmorMaterial.CHAIN;
	
	public ItemSash() {
		super(PIRATE_MATERIAL, EntityEquipmentSlot.CHEST, new Properties().group(Pirate.tab).maxStackSize(1));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return Pirate.MODID + ":textures/armor/sash.png";
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		return new ModelSash();
	}

}
