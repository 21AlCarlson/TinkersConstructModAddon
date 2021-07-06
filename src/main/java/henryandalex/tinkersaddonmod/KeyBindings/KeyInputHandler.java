package henryandalex.tinkersaddonmod.KeyBindings;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.traits.Beam.TraitBeam;
import henryandalex.tinkersaddonmod.traits.Spin2Win.TraitSpin2Win;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

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
				TCAddonMod.instance.getLogger().info("Beam");
				EntityPlayer player = Minecraft.getMinecraft().player;
				ItemStack item = player.getHeldItemMainhand();
				//check if item held is tinker tool with Beam modifier
				TraitBeam.BeamCheck(item, player.world, player, true);					
				break;
			
			case SPIN:
				EntityPlayer player2 = Minecraft.getMinecraft().player;
				ItemStack item2 = player2.getHeldItemMainhand();
				TraitSpin2Win.checkAOEattack(player2, item2);
				break;
			}
		}
	}
}
