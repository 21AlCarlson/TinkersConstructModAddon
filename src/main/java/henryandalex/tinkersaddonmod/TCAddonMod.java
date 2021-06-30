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

import org.apache.logging.log4j.Logger;

import henryandalex.tinkersaddonmod.materials.TCAddonMaterials;
import henryandalex.tinkersaddonmod.proxy.CommonProxy;
import henryandalex.tinkersaddonmod.utils.Reference;;

//Required to load after Tinkers Construct because it uses some of their methods.
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, dependencies = "required-after:tconstruct")
public class TCAddonMod {

	@Instance
	public static TCAddonMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
	public static CommonProxy proxy;
	
    private static Logger logger;
    
    static {
    	// These are needed to make sure the these classes hop on the Event bus used by tinkers
    	TConstruct.pulseManager.registerPulse(new TCAddonIntegration());
    	TConstruct.pulseManager.registerPulse(new TCAddonMaterials());
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
}
