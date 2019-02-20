package net.pirates.mod.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.pirates.mod.Pirate;
import net.pirates.mod.misc.SoundRegistry;

public class ItemTelescope extends Item {
	
	public ItemTelescope() {
		super(PirateItemProperties.BASE_ONE);
		this.addPropertyOverride(new ResourceLocation(Pirate.MODID, "closed"), new IItemPropertyGetter() {
			@Override
			public float call(ItemStack arg0, World arg1, EntityLivingBase arg2) {
				return 0;
			}});
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.setActiveHand(handIn);
		if(!worldIn.isRemote)worldIn.playSound(null, playerIn.getPosition(), SoundRegistry.telescope_open, SoundCategory.PLAYERS, 1F, 1F);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}
	
	@EventBusSubscriber(modid = Pirate.MODID, value = Dist.CLIENT)
	public static class Events{
		
		@SubscribeEvent
		public static void zoom(FOVUpdateEvent event) {
			if(event.getEntity().getActiveItemStack().getItem() == PItems.telescope) {
				event.setNewfov(event.getFov() / 4);
			}
		}
		
		public static final ResourceLocation TELESCOPE_TEXTURE = new ResourceLocation(Pirate.MODID, "textures/overlay/telescope.png");
		
		@SubscribeEvent
		public static void renderOverlay(RenderGameOverlayEvent.Pre event) {
			if(Minecraft.getInstance().player.getActiveItemStack().getItem() == PItems.telescope) {
				GlStateManager.enableAlphaTest();
				Minecraft.getInstance().getTextureManager().bindTexture(TELESCOPE_TEXTURE);
				
				GlStateManager.disableAlphaTest();
				event.setCanceled(true);
			}
		}
	}
}
