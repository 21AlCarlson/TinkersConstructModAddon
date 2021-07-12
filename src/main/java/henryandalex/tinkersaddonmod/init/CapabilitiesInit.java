package henryandalex.tinkersaddonmod.init;

import henryandalex.tinkersaddonmod.capabilities.inventory.ISaveInventorySpaces;
import henryandalex.tinkersaddonmod.capabilities.inventory.InventoryStorage;
import henryandalex.tinkersaddonmod.items.ItemInventory;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilitiesInit {
	
	public static void registerCapabilities() {
		CapabilityManager.INSTANCE.register(ISaveInventorySpaces.class, new InventoryStorage(), ItemInventory::new);
	}
}
