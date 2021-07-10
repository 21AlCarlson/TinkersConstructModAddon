package henryandalex.tinkersaddonmod.entity;

import henryandalex.tinkersaddonmod.utils.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSewerCroc extends RenderLiving<EntitySewerCroc> {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":textures/entity/sewer_croc/sewer_croc.png");

	
	public RenderSewerCroc(RenderManager manager) {
		super(manager, new ModelSewerCroc(), 0.2f);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected ResourceLocation getEntityTexture(EntitySewerCroc entity) {
		return TEXTURE;
	}

}
