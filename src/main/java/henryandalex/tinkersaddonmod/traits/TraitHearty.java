package henryandalex.tinkersaddonmod.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitHearty extends AbstractTrait {
	
	public TraitHearty() {
		super("hearty", TextFormatting.RED);
	}

	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		// drops all the armor of the person hit. (I made it 5% chance)
		double chance = Math.random();
		if (chance < 0.05) {
			player.heal(1);
		}
	}
}
