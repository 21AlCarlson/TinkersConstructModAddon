package henryandalex.tinkersaddonmod.traits;

import java.util.Map;

import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.items.ItemTotemSatchel;
import henryandalex.tinkersaddonmod.utils.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitAutoTotem extends AbstractTrait {

	public TraitAutoTotem() {
		super("auto-totem", TextFormatting.YELLOW);
	}
	
	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
		if( /*isSelected && */ !world.isRemote && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			// since there is only one index in the off hand list, we can just use zero for get(0)
			if(player.inventory.offHandInventory.get(0).isEmpty()) {
				
				for (Map.Entry<Integer, ItemStack> entry : Util.getFromAllPlayerInventories(ItemInit.TOTEM_SATCHEL, player.inventory).entrySet()) {
					if (((ItemTotemSatchel) player.inventory.getStackInSlot(entry.getKey()).getItem()).setTotem(player, player.inventory.getStackInSlot(entry.getKey()))) {
						// break because we already added the totem to the hand and only need to do it once.
						break;
					}
				}
			}
		}
	}
}
