package henryandalex.tinkersaddonmod.traits;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerEvent;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitFinnesse extends AbstractTrait {

	public TraitFinnesse() {
		super("finnesse", 0x706295);
		MinecraftForge.EVENT_BUS.register(this);
		// TODO Auto-generated constructor stub
	}
	
	@SubscribeEvent
	public void onToolBuilding(TinkerEvent.OnItemBuilding event) {
		if(TinkerUtil.hasTrait(event.tag, this.getIdentifier())) {
		      ToolNBT data = TagUtil.getToolStats(event.tag);
		      data.attackSpeedMultiplier = Math.max(1, (data.attack * 130) / 100);
		      TagUtil.setToolTag(event.tag, data.get());
		    }
	}

}
