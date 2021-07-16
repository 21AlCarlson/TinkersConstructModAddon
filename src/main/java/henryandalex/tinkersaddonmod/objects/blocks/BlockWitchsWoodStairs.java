package henryandalex.tinkersaddonmod.objects.blocks;

import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.init.ItemInit;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BlockWitchsWoodStairs extends BlockStairs {

	public BlockWitchsWoodStairs() {
		super(BlockInit.WITCHS_WOOD_PLANKS.getDefaultState());
		
		String name = "witchs_wood_stairs";
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	
		BlockInit.BLOCKS.add(this);
		// maybe set the item registry name as something different and make another blockstate json 
		// file with that different name so it can do stairs#inventory variant
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}
