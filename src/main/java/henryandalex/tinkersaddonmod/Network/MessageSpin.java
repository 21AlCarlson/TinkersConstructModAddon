package henryandalex.tinkersaddonmod.Network;

import henryandalex.tinkersaddonmod.traits.Spin2Win.Spin2WinKnockback;
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
		entityId = buf.getInt(entityId);
		damage = buf.getInt(damage);
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
		Spin2WinKnockback.spinKnockback(player, entity, 2.0);
		entity.attackEntityFrom(DamageSource.GENERIC, message.damage);
	}

	

}
