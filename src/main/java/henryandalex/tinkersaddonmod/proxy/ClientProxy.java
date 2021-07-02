package henryandalex.tinkersaddonmod.proxy;

import henryandalex.tinkersaddonmod.entity.EntityBeam;
import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.traits.Beam.BeamRenderer;
import henryandalex.tinkersaddonmod.traits.Beam.KeyInputHandler;
import henryandalex.tinkersaddonmod.traits.Beam.Keybindings;
import henryandalex.tinkersaddonmod.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Acts as the Client side proxy for sided code. For more info, 
 * see https://mcforge.readthedocs.io/en/latest/concepts/sides/
 */
public class ClientProxy extends CommonProxy {
	
	/**
	 * This has to do with rendering item textures 
	 */
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	@SuppressWarnings("deprecation")
	public static void registerKeyBinds() {
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		for(Keybindings key : Keybindings.values()) {
			ClientRegistry.registerKeyBinding(key.getKeyBind());
		}
	}
	
	
	@Override
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), id));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void render() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBeam.class, new BeamRenderer<EntityBeam>(Minecraft.getMinecraft().getRenderManager(), ItemInit.BEAM, Minecraft.getMinecraft().getRenderItem()));
	}
	
	

	/**
	 * Not sure what this does :P <br>
	 * I don't think it is supposed to go here but i'll
	 * leave it in for a little bit.
	 */
	public static void registerModel() {
	}
}
