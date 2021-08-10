package henryandalex.tinkersaddonmod.events;

import henryandalex.tinkersaddonmod.world.gen.village.MapGenCustomVillageBase;
import henryandalex.tinkersaddonmod.world.gen.village.MapGenVillageHandler;
import net.minecraftforge.fml.common.eventhandler.Event;

/** 
 * This allows People to add their own villages to the system. <br>
 * see more: {@link MapGenVillageHandler#registerVillage(MapGenCustomVillageBase)}
 * 
 * @author AlexC
 *
 */
public class RegisterCustomVillageEvent extends Event {
	
	/**
	 * Subscribe to this method to register your custom-made, biome-specific villages. You can set villages to default 
	 * biomes and not custom ones; however, this is discouraged. 
	 * 
	 * @param t Your custom village that extends MapGenCustomVillageBase. It uses t's instance of villageSpawnBiomes
	 * to select which biomes the village can spawn in.
	 */
	public <T extends MapGenCustomVillageBase> void registerCustomVillage(T t) {
		MapGenVillageHandler.registerVillage(t);
	}
	
	/**
	 * see 
	 * {@link RegisterCustomVillageEvent#registerCustomVillage(T)}
	 */
	public <T extends MapGenCustomVillageBase> void registerCustomVillage(T[] ts) {
		for(T t : ts) {
			MapGenVillageHandler.registerVillage(t);
		}
	}
}
