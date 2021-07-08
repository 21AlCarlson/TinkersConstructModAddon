package henryandalex.tinkersaddonmod.traits;

import henryandalex.tinkersaddonmod.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitFullInvis extends AbstractTrait {
	
	private boolean isInvis;

	public TraitFullInvis() {
		super("full_invisiblity", TextFormatting.WHITE);
		isInvis = false;
	}
	
	// on tool build, get resource location and save it
	
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (isSelected && entity != null) {
			
			try {
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 20));
			}
			catch(ClassCastException e) {}
			// change the texture to nothing? Doesn't work. Going to fix this in the future. 
			// Referencing concealed modifier from ConstructsArmory
			if (!isInvis) {
				tool.getItem().addPropertyOverride(new ResourceLocation(Reference.MODID, "empty"), new IItemPropertyGetter() {
	
					@Override
					@SideOnly(Side.CLIENT)
					public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
						if (isInvis) return 10f;
						else return 0f;
					}
					
				});
				this.isInvis = true;
			}
		}
		else {
			this.isInvis = false;
		}
	}
}

