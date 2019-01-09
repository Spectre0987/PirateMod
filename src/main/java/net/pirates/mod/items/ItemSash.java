package net.pirates.mod.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.pirates.mod.Pirate;
import net.pirates.mod.client.models.ModelSash;

public class ItemSash extends ItemArmor {

	public ItemSash() {
		super(ArmorMaterial.CHAIN, 1, EntityEquipmentSlot.LEGS);
		this.setCreativeTab(Pirate.tab);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return Pirate.MODID + ":textures/armor/sash.png";
	}

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		return new ModelSash();
	}

}
