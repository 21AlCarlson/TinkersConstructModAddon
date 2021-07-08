package henryandalex.tinkersaddonmod.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitShout extends AbstractTrait{
	TraitShout() {
		super("shout", TextFormatting.YELLOW);
	}
	
	// no idea if everyone can see this or just the player using the weapon ¯\_(0-0)_/¯
	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		if (player.getEntityWorld().isRemote) {
			
			String toolName = tool.getDisplayName();
			// I think "((EntityPlayer) target).getDisplayNameString()" works but I have no way of testing in multiplayer right now :/
			String targetName = target instanceof EntityPlayer ? ((EntityPlayer) target).getDisplayNameString() : target.getDisplayName().getUnformattedText();
		
			if (toolName.contains("{$name}")) {

				toolName = toolName.replace("{$name}", targetName);
			}
			else {
				toolName = "<" + targetName + "> " + toolName;
			}
			
			ITextComponent message = new TextComponentTranslation(toolName);
			message.getStyle().setColor(TextFormatting.WHITE);
			player.sendMessage(message);
		}
	}
}
