package henryandalex.tinkersaddonmod.traits;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerEvent;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

/**
 * 
 * @author Henry
 *
 */
public class TraitFragileII extends AbstractTrait {

	public TraitFragileII() {
		super("fragileII", TextFormatting.GRAY);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	  public void onToolBuilding(TinkerEvent.OnItemBuilding event) {
	    if(TinkerUtil.hasTrait(event.tag, this.getIdentifier())) {
	      ToolNBT data = TagUtil.getToolStats(event.tag);
	      // durability is 1, but damage is x5
	      data.durability = 1;
	      data.attack = Math.max(1, data.attack * 5);
	      TagUtil.setToolTag(event.tag, data.get());
	    }
	  }
	
}
