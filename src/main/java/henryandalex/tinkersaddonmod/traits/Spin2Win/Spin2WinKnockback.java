package henryandalex.tinkersaddonmod.traits.Spin2Win;

import org.jline.utils.Log;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Spin2WinKnockback {
	
	//@SideOnly(Side.SERVER)
	public static void spinKnockback(EntityPlayer player, EntityLivingBase target, double knockbackBase) {
		double pX = player.posX;
		double pZ = player.posZ;
		
		double tX = target.posX;
		double tZ = target.posZ;
		
		double deltaX = tX - pX;
		double deltaZ = tZ - pZ;
		
		double angle = Math.tan(deltaZ/deltaX);
		
		double hyp = Math.sqrt((deltaX * deltaX) + (deltaZ * deltaZ));
		
		double kX = knockbackBase * (deltaX/hyp);
		
		double kZ = knockbackBase * (deltaZ/hyp);
		
		Log.info("hello");
		
		target.addVelocity(kX, knockbackBase/2, kZ);
		
		
	}
}
