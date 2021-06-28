package henryandalex.tinkersaddonmod;

import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.Subscribe;

import henryandalex.tinkersaddonmod.materials.TCAddonMaterials;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import slimeknights.mantle.pulsar.pulse.Pulse;

import slimeknights.tconstruct.common.TinkerPulse;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.materials.Material;

@Pulse(id = TCAddonIntegration.PulseId, forced = true)
public class TCAddonIntegration extends TinkerPulse {
	

    public static final String PulseId = "TCAddonIntegration";
    static final Logger log = Util.getLogger(PulseId);
  
    //Should be a subscribe event as seen in the TinkerIntegration class; however, it is not working :/
  	//My fix? Use a method I know is working :D (see preInit in TCAddonMod)
	@Subscribe
    public static void preInit(FMLPreInitializationEvent event) {
    	// test item
    	integrate(TCAddonMaterials.leather);
    	integrate(TCAddonMaterials.tungsten);
    	integrate(TCAddonMaterials.bread);
    	integrate(TCAddonMaterials.glass);
    	
    	TinkerRegistry.addMaterial(TCAddonMaterials.leather);
    	TinkerRegistry.addMaterial(TCAddonMaterials.tungsten);
    	TinkerRegistry.addMaterial(TCAddonMaterials.bread);
    	TinkerRegistry.addMaterial(TCAddonMaterials.glass);
    }
  
    private static MaterialIntegration integrate(Material material) {
    	return add(new MaterialIntegration(material));
    }
    
    private static MaterialIntegration add(MaterialIntegration integration) {
        return TinkerRegistry.integrate(integration);
    }
}
