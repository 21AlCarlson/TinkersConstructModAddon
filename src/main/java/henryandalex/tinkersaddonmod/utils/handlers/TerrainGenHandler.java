package henryandalex.tinkersaddonmod.utils.handlers;

import henryandalex.tinkersaddonmod.world.gen.MapGenVillageExtension;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TerrainGenHandler {
	
	@SubscribeEvent
	public static void createWitchsVillage(InitMapGenEvent event) {
		if(event.getType().equals(InitMapGenEvent.EventType.VILLAGE)) {
			// get Gen type -> if != MapGenVillage (but not extended), then 
			// add current MapGenVillage to the list of MapGenVillages in 
			// MapGenVillage Handler
			// initialize MapGenVillage then set it as the village generator
			MapGenVillageExtension.mapGenVillage = new MapGenVillageExtension();
			event.setNewGen(MapGenVillageExtension.mapGenVillage);
		}
	}
}
