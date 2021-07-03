package henryandalex.tinkersaddonmod.traits.Beam;

import org.jline.utils.Log;

import henryandalex.tinkersaddonmod.Network.MessageBeam;
import henryandalex.tinkersaddonmod.Network.NetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.CooldownTracker;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;

public class TraitBeam extends AbstractTrait {
	
	
	//public static boolean ready;

	public TraitBeam() {
		super("beam", 0x0efde9);
		// TODO Auto-generated constructor stub
	}
	
	
	  public static void BeamCheck(ItemStack tool, World world, Entity entity, boolean isSelected) {
	    if(!isSelected || !(entity instanceof EntityPlayer) || entity.getEntityWorld().isRemote) {
	    	//if(ready == true) {
	    		NBTTagList data = TagUtil.getTraitsTagList(tool);
	    		for(int i = 0; i < data.tagCount(); i++) {
	    			String tag = data.getStringTagAt(i);
	    			Log.info(tag);
	    			if (tag.equals("beam")) {
	    				NetworkHandler.sendToServer(new MessageBeam());
	    				//ready = false;
	    				//Cooldown();
	    				break;
	    			}
	    		//}
	    	}
	    }
	       
	    
	}
	  /*
	  public static void Cooldown() {
	    	for(int i = 0; i < 10000; i++) {

	    	}
	    	ready = true;
	    }*/

}
