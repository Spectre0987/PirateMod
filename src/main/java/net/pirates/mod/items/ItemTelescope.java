package net.pirates.mod.items;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pirates.mod.Pirate;
import net.pirates.mod.helpers.Helper;
import net.pirates.mod.misc.SoundRegistry;

public class ItemTelescope extends Item {
	
	public ItemTelescope() {
		this.setCreativeTab(Pirate.tab);
		this.setMaxStackSize(1);
		this.addPropertyOverride(new ResourceLocation(Pirate.MODID, "closed"), new IItemPropertyGetter() {

			@SideOnly(Side.CLIENT)
			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase ent) {
				return ent != null && ent.getHeldItemMainhand().equals(stack) ? 0.0F : 1.0F;
			}});
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.setActiveHand(handIn);
		if(!worldIn.isRemote)worldIn.playSound(null, playerIn.getPosition(), SoundRegistry.telescope_open, SoundCategory.PLAYERS, 1F, 1F);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	public static int getMagnification(ItemStack stack) {
		if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("mag")) {
			return stack.getTagCompound().getInteger("mag");
		}
		return 4;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	public static void setMagnification(ItemStack telescope, int mag) {
		NBTTagCompound tag = Helper.getTag(telescope);
		tag.setInteger("mag", mag);
		telescope.setTagCompound(tag);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Zoom x" + ItemTelescope.getMagnification(stack));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@EventBusSubscriber(modid = Pirate.MODID, value = Side.CLIENT)
	public static class Events{
		
		@SubscribeEvent
		public static void zoom(FOVUpdateEvent event) {
			ItemStack stack = event.getEntity().getHeldItemMainhand();
			if(event.getEntity().getActiveItemStack().getItem() == PItems.telescope) {
				event.setNewfov(event.getFov() / ItemTelescope.getMagnification(stack));
			}
		}
		
		public static final ResourceLocation TELESCOPE_TEXTURE = new ResourceLocation(Pirate.MODID, "textures/overlay/telescope.png");
		
		@SubscribeEvent
		public static void renderOverlay(RenderGameOverlayEvent.Pre event) {
			if(Minecraft.getMinecraft().player.getActiveItemStack().getItem() == PItems.telescope) {
				GlStateManager.enableAlpha();
				Minecraft.getMinecraft().getTextureManager().bindTexture(TELESCOPE_TEXTURE);
				int width = event.getResolution().getScaledWidth() > event.getResolution().getScaledHeight() ? event.getResolution().getScaledWidth() / 2 : event.getResolution().getScaledHeight() / 2;
				width *= 1.3;
				int posX = event.getResolution().getScaledWidth() / 2 - width / 2, posZ = event.getResolution().getScaledHeight() / 2 - width / 2;
				Helper.drawColoredQuad(0, 0, posX, event.getResolution().getScaledHeight(), 0F, 0F, 0F);
				Helper.drawColoredQuad(0, posZ + width, event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight(), 0, 0, 0);
				Helper.drawColoredQuad(posX + width, 0, event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight(), 0F, 0F, 0F);
				Helper.drawColoredQuad(0, 0, event.getResolution().getScaledWidth(), posZ, 0, 0, 0);
				Helper.drawTexturedQuad(posX, posZ, width, width, 0, 0, 1, 1);
				GlStateManager.disableAlpha();
				event.setCanceled(true);
			}
		}
	}
}
