package henryandalex.tinkersaddonmod.traits;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitAntiGravity extends AbstractTrait{
	
	// In milliseconds
	private static final int TimeWithNoGravity = 3000;
	
	public TraitAntiGravity() {
		super("antigravity", TextFormatting.AQUA);
	}
	
	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		
		target.setNoGravity(true);
		
		TimerTask removeGravity = new TimerTask() {
			@Override
			public void run() {
				target.setNoGravity(false);
			}
		};
		
		Timer timer = new Timer("Remove Gravity");
		
		timer.schedule(removeGravity, TimeWithNoGravity);
	}
}
