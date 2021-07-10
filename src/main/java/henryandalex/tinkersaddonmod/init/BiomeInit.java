package henryandalex.tinkersaddonmod.init;

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
	
	public static void registerBiomes() {
		initBiome(WITCHS_SWAMP, "WitchsSwamp", BiomeType.WARM, Type.WET, Type.SWAMP);
	}
	
	private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types) {
		
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 40));
		
		return biome;
	}
}
