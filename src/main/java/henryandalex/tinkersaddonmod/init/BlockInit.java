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
}
