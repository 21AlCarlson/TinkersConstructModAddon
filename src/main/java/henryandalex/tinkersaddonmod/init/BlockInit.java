package henryandalex.tinkersaddonmod.init;

import java.util.ArrayList;
import java.util.List;
import henryandalex.tinkersaddonmod.objects.blocks.BlockBase;
import henryandalex.tinkersaddonmod.objects.blocks.BlockWitchsGrass;
import henryandalex.tinkersaddonmod.objects.blocks.BlockWitchsWoodLeaves;
import henryandalex.tinkersaddonmod.objects.blocks.BlockWitchsWoodLog;
import henryandalex.tinkersaddonmod.objects.blocks.BlockWitchsWoodSapling;
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
	
	public static final Block WITCHS_GRASS = new BlockWitchsGrass("witchs_grass", Material.GRASS) {
		/*
		@SuppressWarnings("unlikely-arg-type")
		@Override
		public boolean canSustainPlant(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing facing, IPlantable plant) {
			if (plant.equals(Blocks.TALLGRASS) || plant.equals(Blocks.YELLOW_FLOWER) || plant.equals(Blocks.RED_FLOWER) || plant.equals(Blocks.REEDS) || plant.equals(Blocks.DOUBLE_PLANT)) {
				return false;
			}
			else if (plant.equals(Blocks.SAPLING) || plant.equals(BlockInit.WITCHS_WOOD_SAPLING)) {
				return true;
			}
			else {
				return true;
			}
		}
		*/
		
	};
	
	public static final Block CONCRETE_BRICK = new BlockBase("concrete_brick", Material.ROCK);
	
	public static final Block WATER_WASH = new BlockBase("water_wash", Material.IRON);
}
