package henryandalex.tinkersaddonmod.init;

import java.util.ArrayList;
import java.util.List;

import henryandalex.tinkersaddonmod.blocks.BlockBase;
import henryandalex.tinkersaddonmod.blocks.BlockWitchsGrass;
import henryandalex.tinkersaddonmod.blocks.BlockWitchsWoodFence;
import henryandalex.tinkersaddonmod.blocks.BlockWitchsWoodLeaves;
import henryandalex.tinkersaddonmod.blocks.BlockWitchsWoodLog;
import henryandalex.tinkersaddonmod.blocks.BlockWitchsWoodSapling;
import henryandalex.tinkersaddonmod.blocks.BlockWitchsWoodSlab;
import henryandalex.tinkersaddonmod.blocks.BlockWitchsWoodStairs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 *
 */
public class BlockInit {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	
	public static final Block BLOCK_TUNGSTEN = new BlockBase("block_tungsten", Material.IRON);
	
	public static final Block BLOCK_ORE_TUNGSTEN = new BlockBase("block_ore_tungsten", Material.IRON);
	
	public static final Block WITCHS_WOOD_LOG = new BlockWitchsWoodLog("witchs_wood_log");
	public static final Block WITCHS_WOOD_LEAVES = new BlockWitchsWoodLeaves("witchs_wood_leaves");
	public static final Block WITCHS_WOOD_SAPLING = new BlockWitchsWoodSapling("witchs_wood_sapling");
	public static final Block WITCHS_WOOD_PLANKS = new BlockBase("witchs_wood_planks", Material.WOOD);
	public static final Block WITCHS_WOOD_FENCE = new BlockWitchsWoodFence();
	public static final Block WITCHS_WOOD_STAIRS = new BlockWitchsWoodStairs();
	public static final Block WITCHS_WOOD_SLAB = new BlockWitchsWoodSlab();
	
	public static final Block WITCHS_GRASS = new BlockWitchsGrass("witchs_grass", Material.GRASS);
	
	public static final Block CONCRETE_BRICK = new BlockBase("concrete_brick", Material.ROCK);
	
	public static final Block WATER_WASH = new BlockBase("water_wash", Material.IRON);
}
