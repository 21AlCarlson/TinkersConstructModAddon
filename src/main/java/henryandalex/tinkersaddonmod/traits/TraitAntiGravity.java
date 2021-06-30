package henryandalex.tinkersaddonmod.traits;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

/**
 * This trait removes gravity for a few seconds whenever an entity is hit.
 * 
 * @author AlexC
 *
 */
public class TraitAntiGravity extends AbstractTrait {
	
	// In milliseconds
	private static final int TimeWithNoGravity = 3000;
	
	public TraitAntiGravity() {
		super("antigravity", TextFormatting.AQUA);
	}
	
	/**
	 * Every time the tool with this Trait is used to attack a player, this method is called. <br>
	 * It run sets the gravity to false and schedules when it will turn back on again.
	 */ 
	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		
		// Initial method to get rid of gravity
		target.setNoGravity(true);
		
		// Set up the task to turn gravity back on
		TimerTask removeGravity = new TimerTask() {
			@Override
			public void run() {
				target.setNoGravity(false);
			}
		};
		
		// Make the thread for the timer to run on
		Timer timer = new Timer("Remove Gravity");
		
		// Actually start the timer
		timer.schedule(removeGravity, TimeWithNoGravity);
	}
}
