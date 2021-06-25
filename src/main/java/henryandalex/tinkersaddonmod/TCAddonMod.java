package henryandalex.tinkersaddonmod;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

import henryandalex.tinkersaddonmod.materials.TCAddonMaterials;
import henryandalex.tinkersaddonmod.proxy.CommonProxy;
import henryandalex.tinkersaddonmod.utils.Reference;;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, dependencies = "required-after:tconstruct")
public class TCAddonMod {

	@Instance
	public static TCAddonMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
	public static CommonProxy proxy;
	
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	TCAddonIntegration.preInit(event);
    	TCAddonMaterials.preInit(event);
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        TCAddonMaterials.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
    
    
    
    /*
    // this is the class used to register most things
    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
    	@SubscribeEvent
    	public static void registerItems(RegistryEvent.Register<Item> event) {
    		
    	}
    }
    */
}
// register at 714
