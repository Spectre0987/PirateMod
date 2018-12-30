package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;

public class BlockCannonball extends Block implements INeedItem{

	//Number of cannonballs in this stack. (1 cannonball is state 0)
	public static final PropertyInteger NUMBER = PropertyInteger.create("balls", 0, 13);
	
	public ItemCannonball item = new ItemCannonball(this);
	
	public BlockCannonball() {
		super(Material.IRON);
		this.setCreativeTab(Pirate.tab);
		this.setHardness(3F);
		this.setHarvestLevel("pickaxe", -1);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(NUMBER, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(NUMBER);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, NUMBER);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState();
	}

	@Override
	public ItemBlock getItem() {
		return item;
	}
	
	public static class ItemCannonball extends ItemBlock{
		
		public ItemCannonball(Block block) {
			super(block);
		}

		@Override
		public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
			if(worldIn.getBlockState(pos).getBlock() == PBlocks.cannonball && worldIn.getBlockState(pos).getValue(BlockCannonball.NUMBER) < 13) {
				worldIn.setBlockState(pos, PBlocks.cannonball.getDefaultState().withProperty(BlockCannonball.NUMBER, worldIn.getBlockState(pos).getValue(BlockCannonball.NUMBER) + 1));
				return EnumActionResult.SUCCESS;
			}
			return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		}
		
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote && playerIn.getHeldItem(hand).isEmpty()) {
			EntityItem ei = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(this));
			int balls = state.getValue(NUMBER);
			worldIn.spawnEntity(ei);
			if(balls > 0)
				worldIn.setBlockState(pos, PBlocks.cannonball.getDefaultState().withProperty(NUMBER, balls - 1));
			else worldIn.setBlockToAir(pos);
			return true;
		}
		return false;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,int fortune) {
		drops.add(new ItemStack(this, world.getBlockState(pos).getValue(NUMBER) + 1));
	}

}
