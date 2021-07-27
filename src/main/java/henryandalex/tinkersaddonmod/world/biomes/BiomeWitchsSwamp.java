package henryandalex.tinkersaddonmod.world.biomes;

import java.util.Random;

import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.utils.Util;
import henryandalex.tinkersaddonmod.world.gen.WorldGenWitchsWoodTree;
import henryandalex.tinkersaddonmod.world.gen.WorldTypeWitchs;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeWitchsSwamp extends Biome {
	
	public static final String NAME = "Witchs Swamp";
	public static final String REGISTERY_NAME = Util.res(NAME.toLowerCase().replace(" ", "_"));
	
    private final WorldType WORLD_TYPE;
	
	public static final WorldGenAbstractTree TREE = new WorldGenWitchsWoodTree();

	public BiomeWitchsSwamp() {
		super(new BiomeProperties(NAME).setBaseHeight(-0.2F).setHeightVariation(0.1F).setTemperature(0.8F).setRainfall(0.9F).setWaterColor(16747520));
	
		// although instance isn't used, must create the instance to register the WorldType
		// the register line is in the WorldType constructor
		WORLD_TYPE = new WorldTypeWitchs();
		
		topBlock = BlockInit.WITCHS_GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return TREE;
	}
	
	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		return;
	}
	
	public WorldType getWorldType() {
		return this.WORLD_TYPE;
	}
}
