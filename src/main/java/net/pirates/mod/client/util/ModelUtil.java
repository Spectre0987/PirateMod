package net.pirates.mod.client.util;

import net.minecraft.client.model.ModelRenderer;

public class ModelUtil {

	public static void copyAngles(ModelRenderer parent, ModelRenderer child) {
		child.rotateAngleX = parent.rotateAngleX;
		child.rotateAngleY = parent.rotateAngleY;
		child.rotateAngleZ = parent.rotateAngleZ;
	}
}
