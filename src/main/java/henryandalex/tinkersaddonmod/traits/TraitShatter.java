package henryandalex.tinkersaddonmod.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitShatter extends AbstractTrait {

	public TraitShatter() {
		super("shatter", TextFormatting.WHITE);

	}
	
	@Override
	  public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {

	    if(!isSelected || !(entity instanceof EntityPlayer) || entity.getEntityWorld().isRemote) {
	      return;
	    }
	    
	    boolean breakTest = TagUtil.getToolTag(tool).getBoolean(Tags.BROKEN);
	    if ((breakTest) && (entity instanceof EntityPlayer)) {
	    	((EntityPlayer) entity).replaceItemInInventory(itemSlot, ItemStack.EMPTY);
	    	
	    }
	}
}
