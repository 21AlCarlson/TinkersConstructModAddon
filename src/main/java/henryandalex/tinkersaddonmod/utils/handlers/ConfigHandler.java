package henryandalex.tinkersaddonmod.utils.handlers;

import java.io.File;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.utils.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {

	public static Configuration config;
	
	public static int witchsSwampBiomeSpawnRate;
	
	public static void init(File file) {
		
		config = new Configuration(file);
		
		String category = "Biomes";
		
		config.addCustomCategoryComment(category, "Set spawn rate for each biome");
		witchsSwampBiomeSpawnRate = config.getInt("Rate", category, 40, 1, 50, "Spawn Rate for Witchs Swamp biome");
	
		config.save();
	}
	
	public static void registerConfig(FMLPreInitializationEvent event) {
		
		TCAddonMod.config = new File(event.getModConfigurationDirectory() + "/" + Reference.MODID);
		TCAddonMod.config.mkdir();
		init(new File(TCAddonMod.config.getPath(), Reference.MODID + ".cfg"));
	}
}
