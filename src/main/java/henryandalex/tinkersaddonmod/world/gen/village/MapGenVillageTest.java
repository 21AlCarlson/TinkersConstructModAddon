package henryandalex.tinkersaddonmod.world.gen.village;

import henryandalex.tinkersaddonmod.world.gen.village.witchsvillage.MapGenWitchsSwampVillage;
import net.minecraft.world.gen.structure.MapGenVillage;

//TODO: remove this class
public class MapGenVillageTest extends MapGenWitchsSwampVillage{
	public MapGenVillageTest() {
		super();
		villageSpawnBiomes = MapGenVillage.VILLAGE_SPAWN_BIOMES;
	}
}
