package henryandalex.tinkersaddonmod.init;

import henryandalex.tinkersaddonmod.capabilities.inventories.ISaveInventorySpaces;
import henryandalex.tinkersaddonmod.capabilities.inventories.InventoryStorage;
import henryandalex.tinkersaddonmod.items.ItemInventory;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilitiesInit {
	
	public static void registerCapabilities() {
		// Each type specific inventory must be registered separately :(
		CapabilityManager.INSTANCE.register(ISaveInventorySpaces.class, new InventoryStorage(), ItemInventory::new);
	}
}
