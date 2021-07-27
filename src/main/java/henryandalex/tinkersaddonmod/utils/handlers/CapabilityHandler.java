package henryandalex.tinkersaddonmod.utils.handlers;

import henryandalex.tinkersaddonmod.capabilities.inventories.InventoryProvider;
import henryandalex.tinkersaddonmod.items.ItemTotemSatchel;
import henryandalex.tinkersaddonmod.utils.Util;
import net.minecraft.init.Items;
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
    
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	 public static void attachCapability(AttachCapabilitiesEvent<ItemStack> event) {
		if(event.getObject().getItem() instanceof ItemTotemSatchel && !event.getObject().hasCapability(InventoryProvider.INVENTORY_CAP, null)) {
			Thread mainThread = Thread.currentThread();
			// doesn't actually set the capabilities but registers them to be set :(
			event.addCapability(new ResourceLocation(Util.res("inventory_storage")), new InventoryProvider());
			// need init ISaveInventorySpaces but event.getObject().getCapabilities still return null right now.
			// this is icky but oh well. Forge can be a pain in the rear sometimes :(
			new Thread(() -> {
				while (true) {
					if (event.getObject().getCapability(InventoryProvider.INVENTORY_CAP, null) != null) {
						event.getObject().getCapability(InventoryProvider.INVENTORY_CAP, null).initInvSpaceType(Items.TOTEM_OF_UNDYING);
						break;
					}
					else {
						try {
							Thread.sleep(100);
						} 
						catch (InterruptedException e) {
							break;
						}
					}
				}
				try {
					mainThread.join();
				} 
				catch (InterruptedException e) {
					Thread.currentThread().stop();
				}
			}, "init inventory on capability update").start();
		}
	}
}
