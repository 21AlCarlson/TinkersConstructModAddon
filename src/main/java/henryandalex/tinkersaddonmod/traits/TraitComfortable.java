package henryandalex.tinkersaddonmod.traits;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerEvent;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitComfortable extends AbstractTrait {
	
	//This trait is for leather handles/extra, it increases base stats by 130%

	public TraitComfortable() {
		super("comfortable", TextFormatting.GRAY);
		// TODO Auto-generated constructor stub
	}
	
	@SubscribeEvent
	  public void onToolBuilding(TinkerEvent.OnItemBuilding event) {
	    if(TinkerUtil.hasTrait(event.tag, this.getIdentifier())) {
	      ToolNBT data = TagUtil.getToolStats(event.tag);
	      // increase durability, speed, and attack by 30%
	      data.durability = Math.max(1, (data.durability * 130) / 100);
	      data.attack = Math.max(1, (data.attack * 130) / 100);
	      data.speed = Math.max(1, (data.speed * 130) / 100);
	      TagUtil.setToolTag(event.tag, data.get());
	    }
	  }

}
