package henryandalex.tinkersaddonmod.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntitySewerCroc extends EntityPolarBear {

	public EntitySewerCroc(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}
	
	
	public EntitySewerCroc createChild(EntityLiving entity) {
		return new EntitySewerCroc(world);
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return super.getAmbientSound();
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return super.getHurtSound(source);
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return super.getDeathSound();
	}

}
