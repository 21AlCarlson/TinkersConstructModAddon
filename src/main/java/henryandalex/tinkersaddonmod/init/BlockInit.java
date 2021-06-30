package henryandalex.tinkersaddonmod.init;

import java.util.ArrayList;
import java.util.List;

import henryandalex.tinkersaddonmod.objects.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 *
 */
public class BlockInit {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	
	public static final Block BLOCK_TUNGSTEN = new BlockBase("block_tungsten", Material.IRON);
			
	
	public static final Block BLOCK_ORE_TUNGSTEN = new BlockBase("block_ore_tungsten", Material.IRON);
	
	public static final Block WITCHS_WOOD = new BlockBase("witchs_wood", Material.WOOD);
	
	public static final Block STONE_BRICK = new BlockBase("stone_brick", Material.ROCK);
	
	public static final Block WATER_WASH = new BlockBase("water_wash", Material.IRON);

}
