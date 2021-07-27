package henryandalex.tinkersaddonmod.world.gen;

import java.util.Random;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.utils.Util;
import henryandalex.tinkersaddonmod.world.biomes.BiomeWitchsSwamp;
import henryandalex.tinkersaddonmod.world.gen.witchsvillage.MapGenWitchsSwampVillage;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureStart;

/**
 * <p>
 * This class replaces the generic MapGenVillage acting as a sort of wrapper allowing multiple completely custom villages
 * to be added into the game with biome specific spawning. This was a pain in the a-hole to code .-. 
 * </p>
 * <p>
 * This works by checking the biome of the of the world whenever a method is called. It then calls the methods of the 
 * MapGenStructure assigned to that biome. If adding your own biome, check how this mod's custom MapGenStructure classes
 * function
 * </p>
 * 
 * @author AlexC
 *
 */
public class MapGenVillageExtension extends MapGenVillage {
	
	private static final MapGenWitchsSwampVillage MAP_GEN_VILLAGE_CUSTOM = new MapGenWitchsSwampVillage();
	
	public MapGenVillageExtension() {
		super();
	}
		

	/**
	 * Need to be called after this#getStructureStart().
	 * This takes the all Village structures in the component list of the respective map gen village class and builds those components.
	 * Called during in Chunk Populate method
	 */
	@Override
	public synchronized boolean generateStructure(World worldIn, Random randomIn, ChunkPos chunkCoord) {
		int x1 = chunkCoord.getXStart();
		int x2 = chunkCoord.getXEnd();
		int z1 = chunkCoord.getZStart();
		int z2 = chunkCoord.getZEnd();
		if(worldIn.getBiome(new BlockPos(x1, 0, z1)) instanceof BiomeWitchsSwamp || worldIn.getBiome(new BlockPos(x2, 0, z2)) instanceof BiomeWitchsSwamp) {
			return MAP_GEN_VILLAGE_CUSTOM.generateStructure(worldIn, randomIn, chunkCoord);
		}
		else {
			return super.generateStructure(worldIn, randomIn, chunkCoord); // MAP_GEN_VILLAGE_VANILLA.generateStructure(worldIn, randomIn, chunkCoord);
		}
	}
	
	@Override
	public void generate(World worldIn, int x, int z, ChunkPrimer primer) {
		if(worldIn.getBiome(new BlockPos(x, 0, z)) instanceof BiomeWitchsSwamp) {
			MAP_GEN_VILLAGE_CUSTOM.generate(worldIn, z, z, primer);
		}
		else {
			super.generate(worldIn, z, z, primer);
		}
		return;
	}
	

	@Override
	public String getStructureName() {
		return "Village";
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		if(worldIn.getBiome(pos) instanceof BiomeWitchsSwamp) {
			return MAP_GEN_VILLAGE_CUSTOM.getNearestStructurePos(worldIn, pos, findUnexplored);
		}
		return super.getNearestStructurePos(worldIn, pos, findUnexplored);
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		if(Util.getBiomefromChunkCoords(this.world, chunkX, chunkZ) instanceof BiomeWitchsSwamp) {
			return MAP_GEN_VILLAGE_CUSTOM.canSpawnStructureAtCoordsPublic(this.world, chunkX, chunkZ);
		}	
		return super.canSpawnStructureAtCoords(chunkX, chunkZ);
	}

	/**
	 * Adds the components (houses, paths, wells, etc) to the village instance. Does not actually build them.
	 * Called during the Chunk Generate method.
	 */
	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		if(Util.getBiomefromChunkCoords(this.world, chunkX, chunkZ) instanceof BiomeWitchsSwamp) {
			MAP_GEN_VILLAGE_CUSTOM.getStructureStartPublic(chunkX, chunkZ);
			return new StructureStart() {
				@Override 
			    public boolean isValidForPostProcess(ChunkPos pair) {
					/*
					 * This ensures that a village wont be built with this instance of StructureStart 
					 * and consequently the game wont break when super#generateStructure() is called in
					 * this#generateStructure(). This needs to be declared because this StructureStart
					 * is going to be registered to this object's instance of structureMap (which there 
					 * is no way around unless I use an access transformer to remove the final keyword 
					 * for MapGenStructure#recursiveGenerate), and we don't want it to try to build a 
					 * village with this empty instance (chunkPosX and chunkPosZ have not been initialized
					 * in this instance which should theoretically cause bugs but I am too lazy to test 
					 * if it actually does).
					 */
			        return false;
			    }
			};
		}
		//TODO: remove coord printing in final release.
        TCAddonMod.instance.getLogger().info("Vanilla Village @ (Chunk x: " + chunkX + ", Chunk z: " + chunkZ + ")");
        BlockPos pos = Util.getPosFromChunkCoords(chunkX, chunkZ);
        TCAddonMod.instance.getLogger().info("Vanilla Village @ (x: " + pos.getX() + ", z: " + pos.getZ() + ")");
        return super.getStructureStart(chunkX, chunkZ);
	}
}
