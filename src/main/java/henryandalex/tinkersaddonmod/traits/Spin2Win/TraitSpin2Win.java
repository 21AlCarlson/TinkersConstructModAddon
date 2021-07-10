package henryandalex.tinkersaddonmod.traits.Spin2Win;

import java.util.List;

import org.jline.utils.Log;

import henryandalex.tinkersaddonmod.KeyBindings.KeyInputHandler;
import henryandalex.tinkersaddonmod.Network.MessageSpin;
import henryandalex.tinkersaddonmod.Network.NetworkHandler;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitSpin2Win extends AbstractTrait {

	public static List<Entity> entities;
	
	private static int i = 0;
	
	public static boolean ready = false;
	
	public static boolean ready2 = false;
	
	public static double scale = 0;
	
	public static double startX;
	public static double startY;
	public static double startZ;
	
	
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
						startX = player.posX;
						startY = player.posY;
						startZ = player.posZ;
						ready = true;
						KeyInputHandler.secondPress = true;
						break;
					}
				}
			}
	    }
	
	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(ready == true) {
			NBTTagCompound root = TagUtil.getTagSafe(tool);
			ToolNBT data = TagUtil.getToolStats(tool);
			float tempAttack = data.attack;
			data.attack = 0;
			TagUtil.setToolTag(root, data.get());
			if((ready2 == true) || i >= 150) {
				data.attack = tempAttack;
				TagUtil.setToolTag(root, data.get());
				AOEattack(tool, (EntityPlayer)entity);
				ready = false;
				ready2 = false;
				i = 0;
				KeyInputHandler.secondPress = false;
			} else if (tool != ((EntityPlayer)entity).getHeldItemMainhand()){
				ready = false;
				ready2 = false;
				i = 0;
				KeyInputHandler.secondPress = false;
				data.attack = tempAttack;
				TagUtil.setToolTag(root, data.get());
			}
			EntityPlayer player = (EntityPlayer)entity;
			player.motionX = 0;
			player.motionY = 0;
			player.motionZ = 0;
			player.posX = startX;
			player.posY = startY;
			player.posZ = startZ;
			player.moveForward = 0F;
			player.moveStrafing = 0F;
			player.moveVertical = 0F;
			
			scale += 0.01;
			i++;
			Log.info(i);
			
		} else {
			ready2 = false;
			i = 0;
		}
	}
	
	public static void setReadyTrue() {
		ready = true;
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
	    		int damage = (int)Math.round(data.attack * scale);
	    		ToolHelper.damageTool(stack, 2, player);
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
	
	