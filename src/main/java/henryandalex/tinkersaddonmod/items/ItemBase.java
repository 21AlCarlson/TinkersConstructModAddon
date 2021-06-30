package henryandalex.tinkersaddonmod.items;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.utils.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * This class should be the base of all materials. <br>
 * Any non complex materials will initiated in <br>
 * {@link henryandalex.tinkersaddonmod.init.ItemInit} <br>
 * as 'new ItemBase("name")'
 * 
 * @author AlexC
 *
 */
public class ItemBase extends Item implements IHasModel {
	
	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ItemInit.ITEMS.add(this);
	}

	/**
	 * This is how you register Items to Forge. <br>
	 * Must be called during the ModelRegistryEvent.<br>
	 * This is called in: <br>
	 * {@link henryandalex.tinkersaddonmod.utils.handlers.RegistryHandler}
	 */
	@Override
	public void registerModels() {
		TCAddonMod.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
