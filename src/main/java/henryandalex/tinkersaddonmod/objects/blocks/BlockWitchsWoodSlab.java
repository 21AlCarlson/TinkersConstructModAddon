package henryandalex.tinkersaddonmod.objects.blocks;

import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.init.ItemInit;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class BlockWitchsWoodSlab extends BlockSlab {
	
	//public static final PropertyEnum<BlockWitchsWoodSlab.Variant> VARIANT = PropertyEnum.<BlockWitchsWoodSlab.Variant>create("variant", BlockWitchsWoodSlab.Variant.class);
	
	public BlockWitchsWoodSlab() { 
		
		super(Material.WOOD);
		
		IBlockState state = this.blockState.getBaseState();
		
		if (!this.isDouble()) {
			
			state = state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
		}
		
		this.setDefaultState(state /*.withProperty(VARIANT, BlockWitchsWoodSlab.Variant.DEFAULT)*/);
		
		String name = "witchs_wood_slab";
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public String getUnlocalizedName(int meta) {
		
		return super.getUnlocalizedName();
	}

	@Override
	public IProperty<?> getVariantProperty() {
		
		return /*VARIANT*/ null;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		
		return BlockWitchsWoodSlab.Variant.DEFAULT;
	}
	
	@Override
	public boolean isDouble() {
		
		return false;
	}
	
    public IBlockState getStateFromMeta(int meta) {
    	
        IBlockState iblockstate = this.getDefaultState()/*.withProperty(VARIANT, BlockWitchsWoodSlab.Variant.DEFAULT)*/;

        if (!this.isDouble()) {
        	
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    public int getMetaFromState(IBlockState state) {
    	
        int i = 0;

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
        	
            i |= 8;
        }

        return i;
    }

    protected BlockStateContainer createBlockState() {
    	
        return new BlockStateContainer(this, new IProperty[] {HALF/*, VARIANT*/});
    }
	
    public static enum Variant implements IStringSerializable {
    	
        DEFAULT;

        public String getName() {
        	
            return "default";
        }
    }
}
