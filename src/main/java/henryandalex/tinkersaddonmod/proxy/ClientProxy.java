package henryandalex.tinkersaddonmod.proxy;

import henryandalex.tinkersaddonmod.utils.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

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
	
	
	@Override
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), id));
	}

	/**
	 * Not sure what this does :P <br>
	 * I don't think it is supposed to go here but i'll
	 * leave it in for a little bit.
	 */
	public static void registerModel() {
	}
}
