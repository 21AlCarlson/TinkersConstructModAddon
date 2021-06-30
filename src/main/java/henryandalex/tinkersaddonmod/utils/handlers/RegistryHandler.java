package henryandalex.tinkersaddonmod.utils.handlers;

import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.utils.IHasModel;

import slimeknights.tconstruct.common.TinkerOredict;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This is where everything gets registered - items, blocks, <br>
 * and models - with the predefined methods in each class.
 */
@EventBusSubscriber
public class RegistryHandler {
	
	/**
	 * Registers all the blocks and item oredicts.
	 * Note that it's using the item registry event, since it's called after blocks.
	 * This relies on the TinkerOredict pulse being called after the pulses registering the items
	 * See {@link TinerOredict} for initial usage.
	 */
	@SubscribeEvent
	public static void registerItemsToTinkers(RegistryEvent.Register<Item> event) {
		registerCommon();
	}
	
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item : ItemInit.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}
		
		for (Block block : BlockInit.BLOCKS) {
			if (block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
	}
	
	
	private static void registerCommon() {
		TinkerOredict.oredict(Items.LEATHER, "leather");
		TinkerOredict.oredict(ItemInit.TUNGSTEN_INGOT, "tungsten");
	}
}
