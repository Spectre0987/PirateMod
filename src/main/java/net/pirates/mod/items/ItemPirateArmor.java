package net.pirates.mod.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pirates.mod.Pirate;
import net.pirates.mod.client.models.ModelPirateBoots;
import net.pirates.mod.client.models.ModelPirateHat;

public class ItemPirateArmor extends ItemArmor {

	public ItemPirateArmor(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(ArmorMaterial.LEATHER, renderIndexIn, equipmentSlotIn);
		this.setCreativeTab(CreativeTabs.COMBAT);
		this.setCreativeTab(Pirate.tab);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		switch(slot){
		case FEET: return Pirate.MODID + ":textures/armor/boots.png";
		default: return Pirate.MODID + ":textures/armor/pirate.png";
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		switch(armorSlot) {
		case FEET: return new ModelPirateBoots();
		default: return new ModelPirateHat();
		}
	}

}
