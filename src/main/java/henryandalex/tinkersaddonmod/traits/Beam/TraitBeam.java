package henryandalex.tinkersaddonmod.traits.Beam;


import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.Network.MessageBeam;
import henryandalex.tinkersaddonmod.Network.NetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;

public class TraitBeam extends AbstractTrait {
	
	
	public static boolean ready = true;
	
	public static int i = 0;



	public TraitBeam() {
		super("beam", 0x0efde9);
		// TODO Auto-generated constructor stub
	}
	
	
	public static void BeamCheck(ItemStack tool, World world, Entity entity, boolean isSelected) {
		if(!isSelected || !(entity instanceof EntityPlayer) || entity.getEntityWorld().isRemote) {
			if(ready == true) {
			  
				NBTTagList data = TagUtil.getTraitsTagList(tool);
				for(int i = 0; i < data.tagCount(); i++) {
					String tag = data.getStringTagAt(i);
					if (tag.equals("beam")) {
						NetworkHandler.sendToServer(new MessageBeam());
						ready = false;					
						break;
					}
				}
			}
			TCAddonMod.instance.getLogger().info(ready);
	    }
	}
	
	@Override
	  public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
	    // needs to be in hand to be eaten!
	    if(i > 150) {
	    	i = 0;
	    	ready = true;
	    	TCAddonMod.instance.getLogger().info(ready);
	    } else if (ready == false) {
	    	i++;
	    	TCAddonMod.instance.getLogger().info(i);
	    } else {
	    	return;
	    }
	
	
	
	}
}
