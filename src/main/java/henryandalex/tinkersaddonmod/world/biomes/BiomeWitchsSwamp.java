package henryandalex.tinkersaddonmod.world.biomes;

import java.util.Random;

import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.world.gen.WorldGenWitchsWoodTree;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeWitchsSwamp extends Biome {
	
	public static final WorldGenAbstractTree TREE = new WorldGenWitchsWoodTree();

	public BiomeWitchsSwamp() {
		super(new BiomeProperties("WitchsSwamp").setBaseHeight(-0.2F).setHeightVariation(0.1F).setTemperature(0.8F).setRainfall(0.9F).setWaterColor(16747520));
	
		topBlock = BlockInit.WITCHS_GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return TREE;
	}
}