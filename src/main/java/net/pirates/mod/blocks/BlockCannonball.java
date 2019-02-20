package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;

public class BlockCannonball extends BlockWaterloggable implements INeedItem{

	//Number of cannonballs in this stack. (1 cannonball is state 0)
	public static final IntegerProperty NUMBER = IntegerProperty.create("balls", 0, 13);
	
	public ItemCannonball item = new ItemCannonball(this);
	
	public BlockCannonball() {
		super(Properties.create(Material.IRON).hardnessAndResistance(3F));
	}

	@Override
	public ItemBlock getItem() {
		return item;
	}
	
	public static class ItemCannonball extends ItemBlock{
		
		public ItemCannonball(Block block) {
			super(block,new Item.Properties().group(Pirate.tab));
		}

		@Override
		public EnumActionResult onItemUse(ItemUseContext cont) {
			if(cont.getWorld().getBlockState(cont.getPos()).getBlock() == PBlocks.cannonball && cont.getWorld().getBlockState(cont.getPos()).get(BlockCannonball.NUMBER) < 13) {
				cont.getWorld().setBlockState(cont.getPos(), PBlocks.cannonball.getDefaultState().with(BlockCannonball.NUMBER, cont.getWorld().getBlockState(cont.getPos()).get(BlockCannonball.NUMBER) + 1));
				return EnumActionResult.SUCCESS;
			}
			return super.onItemUse(cont);
		}
		
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, IBlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(NUMBER);
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote && player.getHeldItem(hand).isEmpty()) {
			EntityItem ei = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(this));
			int balls = state.get(NUMBER);
			worldIn.spawnEntity(ei);
			if(balls > 0)
				worldIn.setBlockState(pos, PBlocks.cannonball.getDefaultState().with(NUMBER, balls - 1));
			else worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			return true;
		}
		return false;
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState();
	}

}
