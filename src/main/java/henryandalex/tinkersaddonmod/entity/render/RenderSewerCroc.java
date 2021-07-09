package henryandalex.tinkersaddonmod.entity.render;

import henryandalex.tinkersaddonmod.entity.EntitySewerCroc;
import henryandalex.tinkersaddonmod.utils.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSewerCroc extends RenderLiving<EntitySewerCroc> {
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/sewer_croc.png");

	public RenderSewerCroc(RenderManager manager) {
		super(manager, new ModelSewerCroc(), 0.5F);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySewerCroc entity) {
		// TODO Auto-generated method stub
		return TEXTURES;
	}

	@Override
	protected void applyRotations(EntitySewerCroc entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}

}
