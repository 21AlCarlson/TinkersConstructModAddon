package henryandalex.tinkersaddonmod.items;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.util.EnumHelper;
import slimeknights.mantle.item.ItemArmorTooltip;
import slimeknights.tconstruct.library.Util;

public class ItemTotemSatchel extends ItemArmorTooltip {
	
	// private static final int MAX_ENTITY_STACK = 3; // how many Totems can be carried at once
	public static ArmorMaterial TOTEM_SATCHEL_MATERIAL = EnumHelper.addArmorMaterial("PIGGYBACK", Util.resource("piggyback"), 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.BLOCK_SLIME_PLACE, 0);


	public ItemTotemSatchel(ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot equipmentSlot) {
		super(armorMaterial, renderIndex, equipmentSlot);
	}

}
