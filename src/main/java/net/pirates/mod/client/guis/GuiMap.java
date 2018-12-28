package net.pirates.mod.client.guis;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.pirates.mod.Pirate;
import net.pirates.mod.helpers.Helper;

public class GuiMap extends GuiScreen {

	BlockPos pos;
	public static final ResourceLocation TEXTURE = new ResourceLocation(Pirate.MODID, "textures/overlay/map.png");
	public Map<BlockPos, Boolean> water = new HashMap<>();
	
	public GuiMap(BlockPos pos) {
		this.pos = pos;
		water.clear();
	}
	
	public void setPosition(BlockPos pos) {
		this.pos = pos;
		water.clear();
	}
	
	public void setMap(NBTTagList list) {
		water.clear();
		for(NBTBase base : list) {
			NBTTagCompound tag = (NBTTagCompound)base;
			water.put(BlockPos.fromLong(tag.getLong("pos")), tag.getBoolean("water"));
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		EntityPlayer p = Minecraft.getMinecraft().player;
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		Helper.drawTexturedQuad(width / 2 - 125, height / 2 - 125, 250, 250, EnumIcon.MAIN.minU, EnumIcon.MAIN.minV, EnumIcon.MAIN.maxU, EnumIcon.MAIN.maxV);
		int centerX = width / 2;
		int centerY = height / 2;
		double size = 1.5;
		for(BlockPos pos : water.keySet()) {
			if(!water.get(pos))
				Helper.drawTexturedQuad(centerX + (pos.getX() - this.pos.getX()) * size, centerY + (pos.getZ() - this.pos.getZ()) * size, size, size, 0.546875, 0.1875, 0.703125, 0.34375);
		}
		Helper.drawTexturedQuad(width / 2 - 8, height / 2 - 8, 16, 16, 0.5, 0, 0.5546875, 0.0546875);
		int posX = (int)((width / 2) - (pos.getX() - p.posX)), posZ = (int)((height / 2) - (pos.getZ() - p.posZ));
		Helper.drawTexturedQuad(MathHelper.clamp(posX - 4, width / 2 - 125, width / 2 + 125), MathHelper.clamp(posZ - 4, height / 2 - 125, height / 2 + 125), 8, 8, 0.5234375, 0.0703125, 0.578125, 0.140625);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}

	public static enum EnumIcon{
		MAIN(0, 0, 0.5, 0.5);
		
		double minU, minV, maxU, maxV;
		
		EnumIcon(double minU, double minV, double maxU, double maxV){
			this.minU = minU;
			this.minV = minV;
			this.maxU = maxU;
			this.maxV = maxV;
		}
	}
}
