package henryandalex.tinkersaddonmod.blocks;

import java.util.Random;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.utils.IHasModel;
import henryandalex.tinkersaddonmod.world.gen.WorldGenWitchsWoodTree;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class BlockWitchsWoodSapling extends BlockBush implements IGrowable, IHasModel {
	
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	
	public BlockWitchsWoodSapling(String name) {
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// straight from BlockSapling.class
		return (double)worldIn.rand.nextFloat() < 0.45D;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		if (((Integer)state.getValue(STAGE)).intValue() == 0)
        {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {
            generateTree(worldIn, pos, state, rand);
        }
	}
	
    public static void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
        WorldGenTrees worldgenerator =  new WorldGenTrees(true, 4, WorldGenWitchsWoodTree.LOG, WorldGenWitchsWoodTree.LEAF, false);
        
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

        worldgenerator.generate(worldIn, rand, pos);
    }
    
    /*
     * From https://github.com/jabelar/ExampleMod-1.12/blob/master/src/main/java/com/blogspot/jabelarminecraft/examplemod/blocks/BlockSaplingCloud.java
     */
    
    /**
     * Convert the given metadata into a BlockState for this Block.
     *
     * @param meta the meta
     * @return the state from meta
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    /**
     * Convert the BlockState into the correct metadata value.
     *
     * @param state the state
     * @return the meta from state
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(STAGE).intValue() << 3;
        return i;
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.Block#createBlockState()
     */
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {STAGE});
    }
    
    /*
     * (non-Javadoc)
     * @see henryandalex.tinkersaddonmod.utils.IHasModel#registerModels()
     */
    
    @Override
	public void registerModels() {
		TCAddonMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
