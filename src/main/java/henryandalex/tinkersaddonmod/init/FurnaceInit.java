package henryandalex.tinkersaddonmod.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FurnaceInit {
	public static void Init() {
		GameRegistry.addSmelting(BlockInit.BLOCK_ORE_TUNGSTEN, new ItemStack(ItemInit.TUNGSTEN_INGOT, 1), 0.5F);
	}
}
