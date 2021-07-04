package henryandalex.tinkersaddonmod.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitMobMauler extends AbstractTrait {

	public TraitMobMauler() {
		super("mob_mauler", 0x706295);
	}
	
	
	@Override
	  public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
	    if(target.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
	      newDamage += 5.0f; 
	    } else if (target.getCreatureAttribute() == EnumCreatureAttribute.ILLAGER) {
	    	newDamage += 5.0f;
	    } else if(target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
	    	newDamage += 5.0f;
	    } else if(target instanceof EntityCreeper) {
	    	 newDamage += 5.0f;
	    } else if(target instanceof EntityGhast) {
	    	 newDamage += 5.0f;
	    } else if(target instanceof EntitySlime) {
	    	 newDamage += 5.0f;
	    } else if(target instanceof EntityMagmaCube) {
	    	 newDamage += 5.0f;
	    }
	    return super.damage(tool, player, target, damage, newDamage, isCritical);
	  }
	}

