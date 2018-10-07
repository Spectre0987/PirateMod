package net.pirates.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.Pirate;
import net.pirates.mod.client.models.ModelPirateHat;

public class RenderItemHat extends TileEntityItemStackRenderer {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Pirate.MODID, "textures/armor/pirate.png");
	public ModelPirateHat model = new ModelPirateHat();
	Minecraft mc;
	
	public RenderItemHat() {
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
