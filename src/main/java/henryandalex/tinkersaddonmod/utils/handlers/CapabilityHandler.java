package henryandalex.tinkersaddonmod.utils.handlers;

import henryandalex.tinkersaddonmod.capabilities.inventory.InventoryProvider;
import henryandalex.tinkersaddonmod.items.ItemTotemSatchel;
import henryandalex.tinkersaddonmod.utils.Util;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Capability handler
 * 
 * This class is responsible for attaching our capabilities
 */
@EventBusSubscriber
public class CapabilityHandler {
	
	CapabilityHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}
    
	@SubscribeEvent
	 public static void attachCapability(AttachCapabilitiesEvent<ItemStack> event) {
		if(event.getObject().getItem() instanceof ItemTotemSatchel) {
			event.addCapability(new ResourceLocation(Util.resource("inventory_storage")), new InventoryProvider());
		}
	}
}
