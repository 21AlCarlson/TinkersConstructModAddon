package henryandalex.tinkersaddonmod.traits.Beam;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EntitySwing extends Item {
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entity, ItemStack stack) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack item = player.getHeldItemMainhand();
			TraitBeam.BeamCheck(item, player.world, player, true);
		}
		return false;
	}
	
	

}
