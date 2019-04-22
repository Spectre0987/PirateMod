package net.pirates.mod.worldgen;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.pirates.mod.Pirate;
import net.pirates.mod.blocks.PBlocks;
import net.pirates.mod.config.PirateConfig;
import net.pirates.mod.entity.EntityGhostPirate;
import net.pirates.mod.entity.EntityGhostPirate.EnumPirateRank;

public class WorldGenShips implements IWorldGenerator {

	public static final ResourceLocation SHIP = new ResourceLocation(Pirate.MODID, "ship");
	public static final ResourceLocation GHOST_SHIP = new ResourceLocation(Pirate.MODID, "ghost_ship");
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		BlockPos pos = getSurface(world, new BlockPos(chunkX * 16, 0, chunkZ * 16));
		if(PirateConfig.worldGen == null || PirateConfig.worldGen.chance < 0) return;
		if(random.nextInt(PirateConfig.worldGen.chance) == 26 && world.getBiome(pos).equals(Biomes.DEEP_OCEAN)) {
			if(!world.isRemote) {
				Template temp = ((WorldServer)world).getStructureTemplateManager().get(world.getMinecraftServer(), GHOST_SHIP/*random.nextInt(2) == 0 ? GHOST_SHIP : SHIP*/);
				PlacementSettings ps = new PlacementSettings().setIgnoreEntities(true);
				temp.addBlocksToWorld(world, pos.add(8, -1, 8), ps);
				Map<BlockPos, String> map = temp.getDataBlocks(pos.add(8, -1, 8), ps);
				for(BlockPos cp : map.keySet()) {
					String name = map.get(cp);
					TileEntityChest chest = (TileEntityChest)world.getTileEntity(cp.down());
					if(chest != null) {
						if(name.equals("ghost_captian")) {
							chest.setLootTable(new ResourceLocation(Pirate.MODID, "ghost_captain"), random.nextLong());
							EntityGhostPirate pirate = new EntityGhostPirate(world);
							pirate.setPosition(cp.getX() + 0.5, cp.getY(), cp.getZ() + 0.5);
							pirate.setRank(EnumPirateRank.CAPTAIN);
							world.spawnEntity(pirate);
						}
						if(name.equals("ghost_chest"))
							chest.setLootTable(new ResourceLocation("minecraft", "chests/simple_dungeon"), random.nextLong());
					}
					if(name.equals("pirateSpawner")) {
						int max = 8 + random.nextInt(4);
						for(int i = 0; i < max; ++i) {
							BlockPos spawnPos = cp.add(random.nextInt(8) - 4, 0, random.nextInt(8) - 4);
							if(world.getBlockState(cp.down()).getBlock() == PBlocks.ghastly_planks) {
								EntityGhostPirate pirate = new EntityGhostPirate(world);
								pirate.setPosition(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);
								world.spawnEntity(pirate);
								pirate.genRandomGear();
							}
						}
						world.setBlockState(cp, Blocks.AIR.getDefaultState());
					}
				}
			}
		}
	}

	private static BlockPos getSurface(World world, BlockPos pos) {
		for(int i = world.getHeight(); i > 0; i--) {
			BlockPos nPos = new BlockPos(pos.getX(), i, pos.getZ());
			if(world.getBlockState(nPos).getMaterial() == Material.WATER) {
				return nPos;
			}
		}
		return pos;
	}
}
