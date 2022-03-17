package henryandalex.tinkersaddonmod.world.gen.feature.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import henryandalex.tinkersaddonmod.world.biomes.BiomeWitchsSwamp;
import henryandalex.tinkersaddonmod.world.gen.WorldGenWitchsWoodTree;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WitchsWoodTreeGen  implements IWorldGenerator {
	
	private final WorldGenerator WITCHS_WOOD = new WorldGenWitchsWoodTree();
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		
		switch(world.provider.getDimension()) {
			case 1:
				
				break;
				
			case 0:
		
				runGenerator(WITCHS_WOOD, world, random, chunkX, chunkZ, 1, Blocks.GRASS, BiomeWitchsSwamp.class, BiomeForest.class);
				
				break;
				
			case -1:
			
		}
	}
	
	/**
	 * Generates trees during the world building
	 * 
	 * @param generator
	 * @param world
	 * @param random
	 * @param chunkX
	 * @param chunkZ
	 * @param chance How many chunks it will take for a tree to spawn (on average)
	 * @param topBlock
	 * @param classes
	 */
	private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
	{
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock);
		BlockPos pos = new BlockPos(x,y,z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(random.nextInt(chance) == 0)
				{
					generator.generate(world, random, pos);
				}
			}
		}
	}
	
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock)
	{
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = block == topBlock;
		}
		
		return y;
	}

}
