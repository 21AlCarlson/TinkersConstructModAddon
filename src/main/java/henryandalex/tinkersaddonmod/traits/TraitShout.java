package henryandalex.tinkersaddonmod.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitShout extends AbstractTrait{
	TraitShout() {
		super("shout", TextFormatting.YELLOW);
	}
	
	// no idea how to test this in single player :/
	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		ITextComponent message = new TextComponentTranslation(tool.getDisplayName());
		message.getStyle().setColor(TextFormatting.DARK_RED);
		target.sendMessage(message);
	}
}
