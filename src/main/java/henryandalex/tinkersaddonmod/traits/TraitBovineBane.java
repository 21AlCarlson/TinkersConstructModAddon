package henryandalex.tinkersaddonmod.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

/**
 * This trait is for the leather head of tools
 * This trait does 10 extra bonus damage to cows (so they are always a one-shot)
 * This trait also heals the durability of the tool when hitting a cow by 5 
 * 
 * @author Henry
 *
 */

public class TraitBovineBane extends AbstractTrait {
	
	private static float bonusDamage = 10f;
	
	//I think this is for formatting
	public TraitBovineBane() {
		super("bovinebane", TextFormatting.GRAY);
		// TODO Auto-generated constructor stub
	}
	
	
	//overrides damage when hitting a cow, also affects durability
	@Override
	  public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
	    if(target instanceof EntityCow) {
	      newDamage += bonusDamage; //add juicy damage part
	      ToolHelper.healTool(tool, 6, (EntityLivingBase) player); //heal durability part (heals by 5 because hitting makes it go down by 1)
	    }
	    

	    return super.damage(tool, player, target, damage, newDamage, isCritical); //return stuff because its needed i guess
	  }
}
