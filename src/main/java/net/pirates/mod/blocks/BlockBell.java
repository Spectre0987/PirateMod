package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pirates.mod.Pirate;
import net.pirates.mod.entity.EntityKraken;
import net.pirates.mod.misc.SoundRegistry;

public class BlockBell extends BlockHorizontal {

	public BlockBell() {
		super(Material.IRON);
		this.setCreativeTab(Pirate.tab);
		this.setHardness(1F);
		this.setHarvestLevel("axe", -1);
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			for(EntityPlayerMP player : worldIn.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(pos).grow(40))) {
				worldIn.playSound(null, player.getPosition(), SoundRegistry.ship_bell, SoundCategory.BLOCKS, 1F, 1F);
			}
			
			for(EntityKraken kraken : worldIn.getEntitiesWithinAABB(EntityKraken.class, Block.FULL_BLOCK_AABB.offset(pos).grow(64))) {
				kraken.getMoveHelper().setMoveTo(pos.getX() + 0.5, pos.getY(), pos.getZ(), 0.7);
			}
		}
		return true;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
