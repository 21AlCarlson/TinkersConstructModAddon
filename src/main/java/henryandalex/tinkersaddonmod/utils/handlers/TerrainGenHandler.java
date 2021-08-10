package henryandalex.tinkersaddonmod.utils.handlers;

import henryandalex.tinkersaddonmod.world.gen.village.MapGenVillageHandler;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;
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
			MapGenVillageHandler.mapGenVillage = new MapGenVillageHandler();
			
			// This Should be working. Haven't tested it on any other mods tho.
			if (!event.getOriginalGen().equals(new MapGenVillage())) {
				MapGenVillageHandler.registerNewVanillaVillage((MapGenStructure) event.getOriginalGen());
			}
			
			event.setNewGen(MapGenVillageHandler.mapGenVillage);
		}
	}
}
