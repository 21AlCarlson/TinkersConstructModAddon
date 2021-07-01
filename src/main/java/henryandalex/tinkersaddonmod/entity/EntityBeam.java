package henryandalex.tinkersaddonmod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBeam extends EntityThrowable {

	public EntityBeam(World worldIn) {
		super(worldIn);
	}
	
	public EntityBeam(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}
	
	public EntityBeam(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		// TODO Auto-generated method stub
		if(!this.world.isRemote) {
			if(result.entityHit instanceof EntityLivingBase) {
				EntityLivingBase entity = (EntityLivingBase)result.entityHit;
				entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), 5.0f);
			}
			else {
				setDead();
			}
		}
	}

}
