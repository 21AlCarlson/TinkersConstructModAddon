package henryandalex.tinkersaddonmod;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.TConstruct;
import software.bernie.geckolib3.GeckoLib;

import org.apache.logging.log4j.Logger;

import henryandalex.tinkersaddonmod.Network.NetworkHandler;
import henryandalex.tinkersaddonmod.init.CapabilitiesInit;
import henryandalex.tinkersaddonmod.init.EntityInit;
import henryandalex.tinkersaddonmod.init.FurnaceInit;
import henryandalex.tinkersaddonmod.materials.TCAddonMaterials;
import henryandalex.tinkersaddonmod.proxy.ClientProxy;
import henryandalex.tinkersaddonmod.proxy.CommonProxy;
import henryandalex.tinkersaddonmod.utils.Reference;
import henryandalex.tinkersaddonmod.utils.handlers.RegistryHandler;
import henryandalex.tinkersaddonmod.utils.handlers.RenderHandler;;

//Required to load after Tinkers Construct because it uses some of their methods.
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, dependencies = "required-after:tconstruct")
public class TCAddonMod {

	@Instance
	public static TCAddonMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
	public static CommonProxy proxy;
	
    private Logger logger;
    
    static {
    	// These are needed to make sure the these classes hop on the Event bus used by tinkers
    	TConstruct.pulseManager.registerPulse(new TCAddonIntegration());
    	TConstruct.pulseManager.registerPulse(new TCAddonMaterials());
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	RegistryHandler.otherRegistries();
    	CapabilitiesInit.registerCapabilities();
    	logger = event.getModLog();
    	ClientProxy.registerKeyBinds();
    	EntityInit.registerLivingEntities();
    	RenderHandler.registerEntityRenders();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	RegistryHandler.registerEventListeners();
    	FurnaceInit.Init();
    	EntityInit.registerEntities();
    	TCAddonMod.proxy.render();
    	NetworkHandler.init();
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        GeckoLib.initialize();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
    
    public Logger getLogger() {
    	return this.logger;
    }
    
    
}
