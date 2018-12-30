package net.pirates.mod.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.items.PItems;
import net.pirates.mod.tileentity.TileEntityCannon;

public class BlockCannon extends BlockContainer {
	
	public static final AxisAlignedBB BB = new AxisAlignedBB(0, 0, 0, 1, 0.6, 1);
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockCannon() {
		super(Material.IRON);
		this.setCreativeTab(Pirate.tab);
		this.setHarvestLevel("axe", 0);
		this.setHardness(1F);
		this.setResistance(1F);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote && !playerIn.getHeldItem(hand).isEmpty()) {
			Item held = playerIn.getHeldItem(hand).getItem();
			TileEntityCannon cannon = (TileEntityCannon) worldIn.getTileEntity(pos);
			if(cannon == null) return false;
			if(held == Items.GUNPOWDER) {
				cannon.setGunpowder(cannon.getGunpowder() + 1);
				playerIn.getHeldItem(hand).shrink(1);
			}
			else if(held == Item.getItemFromBlock(PBlocks.cannonball) && cannon.isRammed() && !cannon.hasBall()) {
				cannon.setHasBall(true);
				playerIn.getHeldItem(hand).shrink(1);
			}
			else if(held == PItems.ram_rod && cannon.getGunpowder() > 0) {
				playerIn.getHeldItem(hand).damageItem(1, playerIn);
				cannon.setRammed(true);
			}
			else if(held == Items.FLINT_AND_STEEL && cannon.hasBall() && cannon.isRammed() && cannon.getGunpowder() > 0) {
				playerIn.getHeldItem(hand).damageItem(1, playerIn);
				cannon.fire();
			}
		}
		return true;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(this));
		return list;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return BB;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCannon();
	}

}
