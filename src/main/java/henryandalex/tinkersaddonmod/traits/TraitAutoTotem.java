package henryandalex.tinkersaddonmod.traits;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.items.totemsatchel.ItemTotemSatchel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitAutoTotem extends AbstractTrait {

	public TraitAutoTotem() {
		super("Auto-Totem", TextFormatting.YELLOW);
	}
	
	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(!world.isRemote && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			// since there is only one index in the offhand list, we can just use zero for get(0)
			if(player.inventory.offHandInventory.get(0).isEmpty()) {
				/**
				 * {@link net.minecraft.entity.player.InventoryPlayer#hasItemStack()
				 */
				List<NonNullList<ItemStack>> allInventories = Arrays.<NonNullList<ItemStack>>asList(player.inventory.mainInventory, player.inventory.armorInventory);
				
				label23:
					
				for (List<ItemStack> list : allInventories) {
					
					Iterator<ItemStack> iterator = list.iterator();
		
		            while (true) {
		                if (!iterator.hasNext()) {
		                    continue label23;
		                }
		
		                ItemStack itemstack = (ItemStack)iterator.next();
		
		                if (!itemstack.isEmpty() && itemstack.isItemEqual(new ItemStack(ItemInit.TOTEM_SATCHEL))) {
		                	((ItemTotemSatchel) itemstack.getItem()).setTotem(player);
		                }
		            }
				}
			}
		}
	}
}
