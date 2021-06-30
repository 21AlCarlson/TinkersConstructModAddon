package henryandalex.tinkersaddonmod.traits;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

/**
 * This trait will add the effect of removing a single <br>
 * piece of armor from anyone or thing hit by the weapon. <br>
 * It should have red text in the tinkers book but doesn't?
 * 
 * @author AlexC
 *
 */
public class TraitAntiArmor extends AbstractTrait {

	public TraitAntiArmor() {
		super("antiarmor", TextFormatting.RED);
	}

	/**
	 * Every time the tool with this Trait is used to attack a player, this method is called. <br>
	 * It will remove one piece of equipment from the character hit 10% of the time (on average)
	 */
	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		// Checks if the worlds is remote. 
		// When the world is remote, and this code runs, it will spawn in armor that can not be picked up or destroyed.
		if (!player.getEntityWorld().isRemote) {
			
			double chance = Math.random();
			
			if(chance < 0.05) {
			
				// Random number between one and four. 
				// These numbers correspond with the slot index of the armor which is to be dropped
				int randomEquipmentIndex = (int)((Math.random() * 3) + 1);
				
				// Originally in EntityLiving class as method: dropEquipment. Moved here for modification
				for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values()) {
					
					// Checks to make sure the armor is the peice that needs to be dropped and nothing else.
		        	if (entityequipmentslot.getSlotIndex() == randomEquipmentIndex) {
		        		
			            ItemStack itemstack = target.getItemStackFromSlot(entityequipmentslot);
			
			            if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
			                if (itemstack.isItemStackDamageable()) {
			                    itemstack.setItemDamage(itemstack.getMaxDamage() - target.getRNG().nextInt(1 + target.getRNG().nextInt(Math.max(itemstack.getMaxDamage() - 3, 1))));
			                }
			                
			                target.entityDropItem(itemstack, 0.0F);
			                
				            //end of modified dropEquipment method.
	
				            target.setItemStackToSlot(entityequipmentslot, ItemStack.EMPTY);
			            }
		        	}
				}
			}
		}
	}
}
