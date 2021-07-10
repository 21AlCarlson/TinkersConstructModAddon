package henryandalex.tinkersaddonmod.utils.handlers;

import henryandalex.tinkersaddonmod.entity.EntitySewerCroc;
import henryandalex.tinkersaddonmod.entity.RenderSewerCroc;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
	
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySewerCroc.class, new IRenderFactory<EntitySewerCroc>() {
			@Override
			public Render<? super EntitySewerCroc> createRenderFor(RenderManager manager) {
				return new RenderSewerCroc(manager);
			}
		});
	}

}
