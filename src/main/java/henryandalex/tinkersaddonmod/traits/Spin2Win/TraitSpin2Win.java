package henryandalex.tinkersaddonmod.traits.Spin2Win;

import java.util.List;

import org.jline.utils.Log;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.Network.MessageBeam;
import henryandalex.tinkersaddonmod.Network.MessageSpin;
import henryandalex.tinkersaddonmod.Network.NetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import slimeknights.tconstruct.common.TinkerNetwork;
import slimeknights.tconstruct.library.events.TinkerToolEvent;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.tools.ranged.IProjectile;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTools;

public class TraitSpin2Win extends AbstractTrait {

	public static List<Entity> entities;
	
	public TraitSpin2Win() {
		super("spin2win", 0x706295);
		// TODO Auto-generated constructor stub
	}
	
	public static void checkAOEattack(EntityPlayer player, ItemStack tool) {
		
		if(player.getEntityWorld().isRemote) {			  
				NBTTagList data = TagUtil.getTraitsTagList(tool);
				for(int i = 0; i < data.tagCount(); i++) {
					String tag = data.getStringTagAt(i);
					if (tag.equals("spin2win")) {
						AOEattack(tool, player);					
						break;
					}
				}
			}
	    }
	
	public static void AOEattack(ItemStack stack, EntityPlayer player) {
		if(player.getCooledAttackStrength(0.5F) <= 0.9f) {
			return;
		}
		

		
		player.getEntityWorld().playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
	    player.spawnSweepParticles();
	    ToolNBT data = TagUtil.getToolStats(stack);
	    getAoeEntities(player);
    	boolean hit = false;
	    for(Entity entity : entities) {
	    	Log.info(entity.getUniqueID());
	    	if(entity instanceof EntityLivingBase) {
	    		EntityLivingBase entityLiving = (EntityLivingBase)entity;
	    		int id = entityLiving.getEntityId();
	    		int damage = Math.round(data.attack);
	    		NetworkHandler.sendToServer(new MessageSpin(id, damage));
	    	//hit |= Spin2WinAttack.attackEntity(stack, (ToolCore)stack.getItem(), player, entity, null, false);
	    	//attackEntity(stack, (ToolCore)stack.getItem(), player, entity, null, true);
	    	}
	    	
	    }
	    if(hit) {
	        player.resetCooldown();
	      }
	    
	    
	}
	
	private static List<Entity> getAoeEntities(EntityPlayer player) {

	    AxisAlignedBB box = new AxisAlignedBB(player.posX - 3.0D, player.posY - 2.0D, player.posZ - 3.0D, player.posX + 3.0D, player.posY + 2.0D, player.posZ + 3.0D);

	    entities =  player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, box);
	    return entities;
	  }
}
	
	