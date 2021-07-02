package henryandalex.tinkersaddonmod.Network;

import org.jline.utils.Log;

import henryandalex.tinkersaddonmod.entity.EntityBeam;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class MessageBeam extends MessageBase<MessageBeam>{

	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleClientSide(MessageBeam message, EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(MessageBeam message, EntityPlayer player) {
		if(!player.world.isRemote && (player.getMaxHealth() == player.getHealth())) {
				Log.info("hello");
				Vec3d look = player.getLookVec();
				EntityBeam beam = new EntityBeam(player.world, 1.0D, 1.0D, 1.0D);
				beam.setPosition(player.posX + look.x * 1.5D, player.posY + look.y * 1.5D + 1, player.posZ + look.z * 1.5D);
	            beam.setNoGravity(true);
				beam.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
	            player.world.spawnEntity(beam);
	}}

	

}
