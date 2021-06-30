package henryandalex.tinkersaddonmod.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitSalmonella extends AbstractTrait {

	public TraitSalmonella() {
		super("salmonella", TextFormatting.WHITE);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {

		target.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 60));
		target.addPotionEffect(new PotionEffect(MobEffects.POISON, 60));

	}

}
