package henryandalex.tinkersaddonmod.utils.handlers;

import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.utils.IHasModel;

import slimeknights.tconstruct.common.TinkerOredict;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	/**
	 * Registers all the blocks and item oredicts.
	 * Note that it's using the item registry event, since it's called after blocks.
	 * This relies on the TinkerOredict pulse being called after the pulses registering the items
	 * See TinerOredict for initial usage.
	 */
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		registerCommon();
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item : ItemInit.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}
	}
	
	
	private void registerCommon() {
		TinkerOredict.oredict(Items.LEATHER, "leather");
	}
}
