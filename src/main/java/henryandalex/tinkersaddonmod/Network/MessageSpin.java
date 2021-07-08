package henryandalex.tinkersaddonmod.Network;

import org.jline.utils.Log;

import henryandalex.tinkersaddonmod.traits.Spin2Win.Spin2WinKnockback;
import henryandalex.tinkersaddonmod.traits.Spin2Win.TraitSpin2Win;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

public class MessageSpin extends MessageBase<MessageSpin>{
	
	private int entityId;
	private int damage;
	
	public MessageSpin(int entityId, int damage) {
		this.entityId = entityId;
		this.damage = damage;
	}
	
	public MessageSpin() {
		
	}


	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		entityId = buf.readInt();
		damage = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		buf.writeInt(entityId);
		buf.writeInt(damage);
	}

	@Override
	public void handleClientSide(MessageSpin message, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleServerSide(MessageSpin message, EntityPlayer player) {
		// TODO Auto-generated method stub
		EntityLiving entity = (EntityLiving)player.world.getEntityByID(message.entityId);
		Spin2WinKnockback.spinKnockback(player, entity, 2.0 * TraitSpin2Win.scale);
		TraitSpin2Win.scale = 0.0;
		Log.info(message.entityId);
		Log.info(message.damage);
		entity.attackEntityFrom(DamageSource.GENERIC, message.damage);
	}

	

}
