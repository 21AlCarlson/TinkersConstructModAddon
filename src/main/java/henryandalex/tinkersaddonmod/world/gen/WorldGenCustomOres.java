package henryandalex.tinkersaddonmod.world.gen;

import java.util.Random;

import henryandalex.tinkersaddonmod.init.BlockInit;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomOres implements IWorldGenerator {

	private WorldGenerator block_ore_tungsten;

	
	public WorldGenCustomOres() {
		block_ore_tungsten = new WorldGenMinable(BlockInit.BLOCK_ORE_TUNGSTEN.getDefaultState(), 9, BlockMatcher.forBlock(Blocks.STONE));	
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
		
	
		if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore generated out of bounds"); {
			
			int heightDiff = maxHeight - minHeight + 1;
			
			
			for(int i = 0; i < chance; i++) {
				int x = chunkX * 16 + rand.nextInt(16);
				int z = chunkZ * 16 + rand.nextInt(16);
				int y = minHeight + rand.nextInt(heightDiff);
				
				gen.generate(world, rand, new BlockPos(x, y, z));
			}
		}
	
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		
		runGenerator(block_ore_tungsten, world, random, chunkX, chunkZ, 50, 0, 100);
	}
}


