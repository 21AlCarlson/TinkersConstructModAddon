package henryandalex.tinkersaddonmod.objects.blocks;

import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.init.ItemInit;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BlockWitchsWoodFence extends BlockFence {

	public BlockWitchsWoodFence() {
		super(Material.WOOD, MapColor.ADOBE);
		String name = "witchs_wood_fence";
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	
		BlockInit.BLOCKS.add(this);
		// maybe set the item registry name as something different and make another blockstate json 
		// file with that different name so it can do stairs#inventory variant
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}
