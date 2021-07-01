package henryandalex.tinkersaddonmod.traits.Beam;

import org.jline.utils.Log;
import henryandalex.tinkersaddonmod.Network.MessageBeam;
import henryandalex.tinkersaddonmod.Network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import slimeknights.tconstruct.library.tinkering.TinkersItem;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.tools.traits.TraitProgressiveStats.StatNBT;

public class KeyInputHandler {
	private Keybindings getPressedKey() {
		for(Keybindings key : Keybindings.values()) {
			if(key.isPressed()) return key;
		}
		return null;
	}
	
	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		Keybindings key = getPressedKey();
		if(key != null) {
			switch(key) {
			case BEAM:
				Log.info("Beam");
				EntityPlayer player = Minecraft.getMinecraft().player;
				ItemStack item = player.getHeldItemMainhand();
				//check if item held is tinker tool with Beam modifier
				TraitBeam.BeamCheck(item, player.world, player, true);					
				
				break;
			}
		}
	}
}
