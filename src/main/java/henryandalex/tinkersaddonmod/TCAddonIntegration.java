package henryandalex.tinkersaddonmod;

import henryandalex.tinkersaddonmod.materials.TCAddonMaterials;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import slimeknights.tconstruct.common.TinkerPulse;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;

//@Pulse(id = TCAddonIntegration.PulseId, forced = true)
public class TCAddonIntegration extends TinkerPulse {
	

    //public static final String PulseId = "TCAddonIntegration";
    //static final Logger log = Util.getLogger(PulseId);
  
    //Should be a subscribe event as seen in the TinkerMaterials class; however, it is not working :/
  	//My fix? Use a method I know is working :D (see preInit in TCAddonMod)
    public static void preInit(FMLPreInitializationEvent event) {
    	// test item
    	integrate(TCAddonMaterials.leather);
    	
    	TinkerRegistry.addMaterial(TCAddonMaterials.leather);
    }
  
    private static MaterialIntegration integrate(Material material) {
    	return add(new MaterialIntegration(material));
    }
    
    private static MaterialIntegration add(MaterialIntegration integration) {
        return TinkerRegistry.integrate(integration);
    }
}
