package henryandalex.tinkersaddonmod.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitHealthy extends AbstractTrait {
	
	public TraitHealthy() {
		super("healthy", TextFormatting.GOLD);
	}

	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		// drops all the armor of the person hit. (I made it 5% chance)
		double chance = Math.random();
		if ((chance < 0.5) && (player instanceof EntityPlayer)) {
			((EntityPlayer) player).getFoodStats().addStats(1, 0);
		}
	}
}