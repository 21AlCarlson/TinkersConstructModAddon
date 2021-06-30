package henryandalex.tinkersaddonmod.proxy;

import net.minecraft.item.Item;

/**
 * Not really sure why but it is usually standard to name the Server proxy Common Proxy. ¯\_(o.o)_/¯
 * For more info see https://mcforge.readthedocs.io/en/latest/concepts/sides/
 * 
 * @author AlexC
 *
 */
public class CommonProxy {
	public void registerItemRenderer(Item item, int meta, String id) {}
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {}
}
