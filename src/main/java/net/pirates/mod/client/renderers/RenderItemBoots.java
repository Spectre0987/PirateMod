package net.pirates.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.pirates.mod.Pirate;
import net.pirates.mod.client.models.ModelPirateBoots;

public class RenderItemBoots extends TileEntityItemStackRenderer {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Pirate.MODID, "textures/armor/boots.png");
	public ModelPirateBoots model = new ModelPirateBoots();
	Minecraft mc;
	
	public RenderItemBoots() {
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
