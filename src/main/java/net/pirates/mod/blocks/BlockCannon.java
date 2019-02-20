package net.pirates.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.pirates.mod.items.PItems;
import net.pirates.mod.tileentity.TileEntityCannon;

public class BlockCannon extends BlockContainer {
	
	public static final AxisAlignedBB BB = new AxisAlignedBB(0, 0, 0, 1, 0.6, 1);
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public BlockCannon() {
		super(Properties.create(Material.IRON).hardnessAndResistance(5F));
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new TileEntityCannon();
	}


	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}


	@Override
	protected void fillStateContainer(Builder<Block, IBlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}


	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		return new IItemProvider() {
			@Override
			public Item asItem() {
				return PBlocks.cannon.asItem();
			}};
	}


	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlayer().getHorizontalFacing());
	}


	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote && !player.getHeldItem(hand).isEmpty()) {
			Item held = player.getHeldItem(hand).getItem();
			TileEntityCannon cannon = (TileEntityCannon) worldIn.getTileEntity(pos);
			if(cannon == null) return false;
			if(held == Items.GUNPOWDER) {
				cannon.setGunpowder(cannon.getGunpowder() + 1);
				player.getHeldItem(hand).shrink(1);
			}
			else if(held == PBlocks.cannonball.asItem() && cannon.isRammed() && !cannon.hasBall()) {
				cannon.setHasBall(true);
				player.getHeldItem(hand).shrink(1);
			}
			else if(held == PItems.ram_rod && cannon.getGunpowder() > 0) {
				player.getHeldItem(hand).damageItem(1, player);
				cannon.setRammed(true);
			}
			else if(held == Items.FLINT_AND_STEEL && cannon.hasBall() && cannon.isRammed() && cannon.getGunpowder() > 0) {
				player.getHeldItem(hand).damageItem(1, player);
				cannon.fire();
			}
		}
		return true;
	}

}
