package henryandalex.tinkersaddonmod.traits;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerEvent;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitFragile extends AbstractTrait {

	public TraitFragile() {
		super("fragile", TextFormatting.GRAY);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	  public void onToolBuilding(TinkerEvent.OnItemBuilding event) {
	    if(TinkerUtil.hasTrait(event.tag, this.getIdentifier())) {
	      ToolNBT data = TagUtil.getToolStats(event.tag);
	      // durability is 0.25, but damage is x2
	      data.durability = Math.max(1, data.durability * 2);
	      data.attack = Math.max(1, data.attack * 5);
	      TagUtil.setToolTag(event.tag, data.get());
	    }
	  }
	
}

