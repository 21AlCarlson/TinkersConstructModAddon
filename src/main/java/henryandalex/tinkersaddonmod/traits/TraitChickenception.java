package henryandalex.tinkersaddonmod.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketCombatEvent.Event;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitChickenception extends AbstractTrait {

	public TraitChickenception() {
		super("chickenception", TextFormatting.WHITE);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		double chance = Math.random();
		if (chance < 0.1) {
			EntityChicken entitychicken = new EntityChicken(player.world);
            entitychicken.setGrowingAge(0);
            entitychicken.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, 0.0F);
            player.world.spawnEntity(entitychicken);
		}
		
	}
	
}
