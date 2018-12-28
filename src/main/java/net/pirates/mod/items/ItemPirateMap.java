package net.pirates.mod.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.pirates.mod.Pirate;
import net.pirates.mod.blocks.PBlocks;
import net.pirates.mod.client.guis.GuiMap;
import net.pirates.mod.helpers.Helper;
import net.pirates.mod.tileentity.TileEntityPirateChest;

public class ItemPirateMap extends Item {
	
	Random rand = new Random();
	
	public ItemPirateMap() {
		this.setCreativeTab(Pirate.tab);
		this.setMaxStackSize(1);
	}

	public static BlockPos getPos(ItemStack stack) {
		return Helper.getTag(stack).hasKey("pos") ? BlockPos.fromLong(stack.getTagCompound().getLong("pos")) : BlockPos.ORIGIN;
	}
	
	public static void setPos(ItemStack stack, BlockPos pos) {
		NBTTagCompound tag = Helper.getTag(stack);
		tag.setLong("pos", pos.toLong());
		stack.setTagCompound(tag);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(!worldIn.isRemote) {
			if(BlockPos.ORIGIN.equals(getPos(stack))) {
				List<Biome> bList = new ArrayList<Biome>();
				bList.add(Biomes.BEACH);
				BlockPos pos = worldIn.getBiomeProvider().findBiomePosition((int)playerIn.posX, (int)playerIn.posZ, 100, bList, new Random(5484654));
				System.out.println("Biome pos: " + pos);
				if(pos != null && !BlockPos.ORIGIN.equals(pos)) {
					for(int i = 0; i < 16 * 16; ++i) {
						BlockPos cPos = worldIn.getTopSolidOrLiquidBlock(pos.add(rand.nextInt(15), worldIn.getHeight(), rand.nextInt(15))).add(0, -2, 0);
						if(worldIn.getBlockState(cPos).getMaterial() == Material.SAND && worldIn.getBlockState(cPos.up()).getMaterial() == Material.SAND) {
							worldIn.setBlockState(cPos, PBlocks.pirate_chest.getDefaultState());
							TileEntityPirateChest chest = (TileEntityPirateChest)worldIn.getTileEntity(cPos);
							if(chest != null) {
								chest.setLoot(new ResourceLocation(Pirate.MODID, "cursed_chest"));
							}
							setPos(stack, cPos);
							NBTTagList data = new NBTTagList();
							for(int x = pos.getX() - 64; x < pos.getX() + 64; ++x) {
								for(int z = pos.getZ() - 64; z < pos.getZ() + 64; ++z) {
									IBlockState state = worldIn.getBlockState(worldIn.getTopSolidOrLiquidBlock(new BlockPos(x, worldIn.getActualHeight(), z)));
									NBTTagCompound tag = new NBTTagCompound();
									tag.setLong("pos", new BlockPos(x, 0, z).toLong());
									tag.setBoolean("water", state.getMaterial() == Material.WATER);
									data.appendTag(tag);
								}
							}
							stack.getTagCompound().setTag("data", data);
							break;
						}
					}
				}
					
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@EventBusSubscriber(modid = Pirate.MODID, value = Side.CLIENT)
	public static class Events{
		
		static GuiMap map = new GuiMap(BlockPos.ORIGIN);
		
		@SubscribeEvent
		public static void showMap(RenderGameOverlayEvent.Pre event) {
			if(event.getType() == ElementType.CROSSHAIRS) {
				ItemStack stack = Minecraft.getMinecraft().player.getHeldItemMainhand();
				if(stack.getItem() == PItems.map && !BlockPos.ORIGIN.equals(getPos(stack))) {
					GlStateManager.enableAlpha();
					map.setPosition(getPos(stack));
					if(map.water.isEmpty())map.setMap(stack.getTagCompound().getTagList("data", NBT.TAG_COMPOUND));
					map.width = event.getResolution().getScaledWidth();
					map.height = event.getResolution().getScaledHeight();
					map.drawScreen(0, 0, Minecraft.getMinecraft().getRenderPartialTicks());
					event.setCanceled(true);
					GlStateManager.disableAlpha();
				}
			}
		}
	}
}
