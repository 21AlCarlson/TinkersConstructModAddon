package henryandalex.tinkersaddonmod.blocks;

import java.util.Random;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWitchGrass extends Block implements IHasModel {

	public BlockWitchGrass(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
        this.setTickRandomly(true);

	
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	

	@Override
	public void registerModels() {
		TCAddonMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");	
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		if (!worldIn.isRemote)
        {
            if (!worldIn.isAreaLoaded(pos, 2)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2)
            {
                worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
            }
            else
            {
                if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                        IBlockState iblockstate = worldIn.getBlockState(blockpos);
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos.up());

                        if (iblockstate.getBlock() == Blocks.DIRT && iblockstate.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT && worldIn.getLightFromNeighbors(blockpos.up()) >= 4 && iblockstate1.getLightOpacity(worldIn, blockpos.up()) <= 2)
                        {
                            worldIn.setBlockState(blockpos, this.getDefaultState());
                        } 
                    }
                }
            }
        }
    }
}
