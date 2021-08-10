package henryandalex.tinkersaddonmod.world.gen.village;

import java.util.List;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public abstract class MapGenCustomVillageBase extends MapGenStructure {
	
	// This is semi-Static
	public static List<Biome> villageSpawnBiomes;
    protected int size;
    protected int distance;
    
    protected MapGenCustomVillageBase() {
    	this.distance = 32;
    }

	public final StructureStart getStructureStartPublic(int chunkX, int chunkZ) {
		StructureStart val = this.getStructureStart(chunkX, chunkZ);
		/* 
		 * Since this is accessed outside of the class, there is no way to put the new instance 
		 * of the StartStructure into the structureMap which stores all the Villages. This is 
		 * usually done right after calling this method in MapGenStructure#recursiveGenerate() 
		 * Therefore, you need to add it here before you return the new StrucutreStart.
		 */
		this.structureMap.put(ChunkPos.asLong(chunkX, chunkZ), val);
		return val;
	}

	public final boolean canSpawnStructureAtCoordsPublic(World world, int chunkX, int chunkZ) {
		this.world = world;
		return this.canSpawnStructureAtCoords(chunkX, chunkZ);
	}
	
	public abstract List<Biome> getVillageSpawnBiomes();
}
