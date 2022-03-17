package henryandalex.tinkersaddonmod.world.biomes;

import java.util.Random;

import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.utils.Util;
import henryandalex.tinkersaddonmod.world.gen.WorldGenWitchsWoodTree;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeWitchsSwamp extends Biome {
	
	public static final String NAME = "Witchs Swamp";
	public static final String REGISTERY_NAME = Util.res(NAME.toLowerCase().replace(" ", "_"));
	
	public static final WorldGenAbstractTree TREE = new WorldGenWitchsWoodTree();

	public BiomeWitchsSwamp() {
		super(new BiomeProperties(NAME).setBaseHeight(-0.2F).setHeightVariation(0.1F).setTemperature(0.8F).setRainfall(0.9F));
		
		topBlock = BlockInit.WITCHS_GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.extraTreeChance = 0.5f;
		this.decorator.treesPerChunk = 3;
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return TREE;
	}
}
