package net.pirates.mod.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.pirates.mod.Pirate;
import net.pirates.mod.client.models.ModelBarbossaHat;
import net.pirates.mod.client.models.ModelPirateHat;

public class ItemPirateClothes extends ItemArmor {

	int type = 0;
	public ItemPirateClothes(int type) {
		super(ArmorMaterial.CHAIN, 0, EntityEquipmentSlot.HEAD);
		this.setCreativeTab(Pirate.tab);
		this.type = type;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return Pirate.MODID + (this.type == 0 ? ":textures/entity/hat.png" : ":textures/entity/barbossa_hat.png");
	}

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		return this.type == 0 ? new ModelPirateHat() : new ModelBarbossaHat();
	}

}
