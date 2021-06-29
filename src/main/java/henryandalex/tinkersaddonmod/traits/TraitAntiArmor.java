package henryandalex.tinkersaddonmod.traits;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitAntiArmor extends AbstractTrait {

	public TraitAntiArmor() {
		super("antiarmor", TextFormatting.RED);
	}

	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		// drops all the armor of the person hit. (I made it 5% chance)
		//double chance = Math.random();
		//if (chance < 0.05) {
		//	target.getArmorInventoryList().forEach(armor -> target.entityDropItem(armor, 0));
		//  target.getArmorInventoryList().forEach(armor -> target.replaceItemInInventory(armor, null)
		//}
		
		if (!player.getEntityWorld().isRemote) {
			// Originally in EntityLiving class as method: dropEquipment. Moved here for modification
			for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values()) {
	        	if (entityequipmentslot.getSlotType().equals(EntityEquipmentSlot.Type.ARMOR)) {
	        		
		            ItemStack itemstack = target.getItemStackFromSlot(entityequipmentslot);
		
		            if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
		                if (itemstack.isItemStackDamageable()) {
		                    itemstack.setItemDamage(itemstack.getMaxDamage() - target.getRNG().nextInt(1 + target.getRNG().nextInt(Math.max(itemstack.getMaxDamage() - 3, 1))));
		                }
		                
		                target.entityDropItem(itemstack, 0.0F);
		            }
		            //end of modified dropEquipment method.
		            
		            target.setItemStackToSlot(entityequipmentslot, ItemStack.EMPTY);
	        	}
			}
		}
	}
}
