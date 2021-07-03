package henryandalex.tinkersaddonmod.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitBossSlayer extends AbstractTrait {

	public TraitBossSlayer() {
		super("boss_slayer", 0x0efde9);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
	    
		if (target instanceof EntityWither || target instanceof EntityDragon) {
			newDamage = damage * 2;
		}
		
		return super.damage(tool, player, target, damage, newDamage, isCritical);
	}

}
