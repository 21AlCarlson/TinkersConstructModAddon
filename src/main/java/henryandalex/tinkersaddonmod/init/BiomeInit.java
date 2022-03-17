package henryandalex.tinkersaddonmod.init;

import henryandalex.tinkersaddonmod.utils.handlers.ConfigHandler;
import henryandalex.tinkersaddonmod.world.biomes.BiomeWitchsSwamp;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit {
	
	public static final Biome WITCHS_SWAMP = new BiomeWitchsSwamp();
	
    // Must be called on the RegistryEvent.Register<Biome> which is in the registry handler.
	public static void registerBiomes() {
		initBiome(WITCHS_SWAMP, BiomeWitchsSwamp.REGISTERY_NAME, BiomeType.WARM, Type.WET, Type.FOREST);
	}
	
	private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types) {
		
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, ConfigHandler.witchsSwampBiomeSpawnRate));
		BiomeManager.addSpawnBiome(biome);
		//BiomeManager.addVillageBiome(biome, true);
		
		return biome;
	}
}
