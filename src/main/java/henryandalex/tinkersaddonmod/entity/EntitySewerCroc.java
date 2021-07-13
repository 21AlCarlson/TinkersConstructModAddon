package henryandalex.tinkersaddonmod.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntitySewerCroc extends EntityMob {

	public EntitySewerCroc(World worldIn) {
		super(worldIn);
		this.setSize(width, height);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(3, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.8D, true));
		this.tasks.addTask(3, new EntityAIWander(this, 0.3D));
		this.tasks.addTask(3, new EntityAIBreakDoor(this));
		this.tasks.addTask(1, new EntityAIAvoidEntity<EntitySewerCroc>(this, EntitySewerCroc.class, 5.0F, 0.4, 0.4));
		
		this.applyEntityAI();
	}
	
	protected void applyEntityAI()
    {
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityMob>(this, EntityMob.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityAnimal>(this, EntityAnimal.class, false));

    }
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15.0);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0);
		/*this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.0);*/
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0);
		
	}

	/*@Override
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
	
	@Override
	protected ResourceLocation getLootTable() {
		return super.getLootTable();
	}*/

}
