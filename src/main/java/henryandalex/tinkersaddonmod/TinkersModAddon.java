package henryandalex.tinkersaddonmod;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.apache.logging.log4j.Logger;
import henryandalex.tinkersaddonmod.utils.Reference;;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class TinkersModAddon {

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    
    // this is the class used to register most things
    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
    	@SubscribeEvent
    	public static void registerItems(RegistryEvent.Register<Item> event) {
    		
    	}
    }
}
// register at 714
