package henryandalex.tinkersaddonmod.items;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.utils.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.util.EnumHelper;
import slimeknights.mantle.item.ItemArmorTooltip;
import slimeknights.tconstruct.library.Util;

public class ItemTotemSatchel extends ItemArmorTooltip implements IHasModel {
	
	// private static final int MAX_TOTEM_STACK = 3; // how many Totems can be carried at once
	public static ArmorMaterial TOTEM_SATCHEL_MATERIAL = EnumHelper.addArmorMaterial("TOTEMSATCHEL", Util.resource("totem_satchel"), 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.BLOCK_SLIME_PLACE, 0);


	public ItemTotemSatchel(String name) {
		super(TOTEM_SATCHEL_MATERIAL, 0, EntityEquipmentSlot.CHEST);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ItemInit.ITEMS.add(this);
	}


	@Override
	public void registerModels() {
		TCAddonMod.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	

}
