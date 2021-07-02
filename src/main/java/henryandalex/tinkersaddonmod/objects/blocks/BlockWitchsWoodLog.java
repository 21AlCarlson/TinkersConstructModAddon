package henryandalex.tinkersaddonmod.objects.blocks;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.utils.IHasModel;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockWitchsWoodLog extends BlockLog implements IHasModel {
	
	public BlockWitchsWoodLog(String name) {
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	// all of this except the last function is from 
	// https://github.com/jabelar/ExampleMod-1.12/blob/master/src/main/java/com/blogspot/jabelarminecraft/examplemod/blocks/BlockLogCloud.java
    /**
     * Get the MapColor for this Block and the given BlockState.
     *
     * @param state the state
     * @param worldIn the world in
     * @param pos the pos
     * @return the map color
     */
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return Blocks.LOG.getDefaultState().getMapColor(worldIn, pos);
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block.
     *
     * @param meta the meta
     * @return the state from meta
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = this.getDefaultState();

        switch (meta & 12)
        {
            case 0:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return state;
    }

    /**
     * Convert the BlockState into the correct metadata value.
     *
     * @param state the state
     * @return the meta from state
     */
    @Override
    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state)
    {
        int meta = 0;

        switch (state.getValue(LOG_AXIS))
        {
            case X:
                meta |= 4;
                break;
            case Z:
                meta |= 8;
                break;
            case NONE:
                meta |= 12;
        }

        return meta;
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.BlockRotatedPillar#createBlockState()
     */
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {LOG_AXIS});
    }

    /* (non-Javadoc)
     * @see net.minecraft.block.BlockRotatedPillar#getSilkTouchDrop(net.minecraft.block.state.IBlockState)
     */
    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1);
    }
	
	@Override
	public void registerModels() {
		TCAddonMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

}
