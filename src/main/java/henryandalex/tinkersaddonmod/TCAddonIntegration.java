package henryandalex.tinkersaddonmod;

import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.Subscribe;

import henryandalex.tinkersaddonmod.fluids.TCAddonFluids;
import henryandalex.tinkersaddonmod.materials.TCAddonMaterials;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import slimeknights.mantle.pulsar.pulse.Pulse;

import slimeknights.tconstruct.common.TinkerPulse;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.materials.Material;

/**
 * This deals with the integrating all materials with tinkers. <br>
 * See {@link TinkerIntegration}
 * 
 * @author AlexC
 *
 */
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
    	integrate(TCAddonMaterials.tungsten, TCAddonFluids.tungsten, "Tungsten").toolforge();
    	integrate(TCAddonMaterials.bread);
    	integrate(TCAddonMaterials.glass);
    	integrate(TCAddonMaterials.chicken);
    	integrate(TCAddonMaterials.mushroom);
    	integrate(TCAddonMaterials.master);
    	integrate(TCAddonMaterials.superior_steel);
    	
    	TinkerRegistry.addMaterial(TCAddonMaterials.leather);
    	TinkerRegistry.addMaterial(TCAddonMaterials.tungsten);
    	TinkerRegistry.addMaterial(TCAddonMaterials.bread);
    	TinkerRegistry.addMaterial(TCAddonMaterials.glass);
    	TinkerRegistry.addMaterial(TCAddonMaterials.chicken);
    	TinkerRegistry.addMaterial(TCAddonMaterials.mushroom);
    	TinkerRegistry.addMaterial(TCAddonMaterials.master);
    	TinkerRegistry.addMaterial(TCAddonMaterials.superior_steel);
    }
  
    private static MaterialIntegration integrate(Material material) {
    	return add(new MaterialIntegration(material));
    }
    
    @SuppressWarnings("unused")
	private static MaterialIntegration integrate(Material material, Fluid fluid) {
    	return add(new MaterialIntegration(material, fluid));
    }
    
    private static MaterialIntegration integrate(Material material, Fluid fluid, String oreSuffix) {
    	return add(new MaterialIntegration(material, fluid, oreSuffix));
    }
    
    private static MaterialIntegration add(MaterialIntegration integration) {
        return TinkerRegistry.integrate(integration);
    }
}
