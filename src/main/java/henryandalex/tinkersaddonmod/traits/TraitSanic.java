package henryandalex.tinkersaddonmod.traits;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

/**
 * Gotta go fast!!
 * 
 * @author AlexC
 *
 */
public class TraitSanic extends AbstractTrait {
	
	// how fast the character will accelerate
	private static final int motionAmplifier = 6;

	public TraitSanic() {
		super("Sanic", TextFormatting.BLUE);
	}
	
	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		int motionAmplifierX = (player.motionX < 0 ? -motionAmplifier : motionAmplifier);
		int motionAmplifierZ = (player.motionZ < 0 ? -motionAmplifier : motionAmplifier);
		
		// possibly have it repeat a few times???
		
		// set up the task
		TimerTask runFaster = new TimerTask() {
			@Override
			public void run() {
				player.addVelocity(motionAmplifierX, 0, motionAmplifierZ);
			}
		};
		
		// Make the thread for the timer to run on
		Timer timer = new Timer("I'm Fast AF Boi");
				
		// Actually start the timer
		timer.schedule(runFaster, 0);
	}
}
