package net.pirates.mod.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.tileentity.TileEntityBoatSling;

public class BlockSling extends BlockContainer {

	public static final AxisAlignedBB RANGE = new AxisAlignedBB(0, -20 ,0, 1, 1, 1);
	public BlockSling() {
		super(Material.WOOD);
		this.setCreativeTab(Pirate.tab);
		this.setHardness(1F);
		this.setResistance(1F);
		this.setHarvestLevel("axe", 0);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.byHorizontalIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockHorizontal.FACING);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBoatSling();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
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
		TileEntityBoatSling sling = (TileEntityBoatSling)worldIn.getTileEntity(pos);
		if(sling != null) {
			if(sling.getBoat() == null){
				for(EntityBoat boat : worldIn.getEntitiesWithinAABB(EntityBoat.class, this.RANGE.offset(pos))) {
					sling.haulBoat(boat);
					break;
				}
			}
			else if(!worldIn.isRemote){
				BlockPos off = pos.offset(worldIn.getBlockState(pos).getValue(BlockHorizontal.FACING));
				Entity e = sling.getBoat();
				e.setPosition(off.getX() + 0.5, off.getY() - 1, off.getZ() + 0.5);
				worldIn.spawnEntity(e);
				sling.setBoat(null);
				playerIn.startRiding(e);
			}
		}
		return true;
	}

}
