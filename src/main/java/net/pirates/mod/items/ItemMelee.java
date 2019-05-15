package net.pirates.mod.items;

import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemSword;

public class ItemMelee extends ItemSword {

	public double speed;
	
	public ItemMelee(ToolMaterial material, double speed) {
		super(material);
		this.speed = speed;
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot slot) {
		Multimap<String, AttributeModifier> att = super.getItemAttributeModifiers(slot);
		if(slot == EntityEquipmentSlot.MAINHAND) {
			att.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName());
			att.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -1, 0));
		}
		return att;
	}

}
