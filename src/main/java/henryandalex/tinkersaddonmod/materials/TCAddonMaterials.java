package henryandalex.tinkersaddonmod.materials;

import com.google.common.eventbus.Subscribe;

import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.traits.TraitsAdded;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import slimeknights.mantle.pulsar.pulse.Pulse;

import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.library.utils.HarvestLevels;

import static slimeknights.tconstruct.library.materials.MaterialTypes.HEAD;

/**
 * Contains all the materials for Tinkers Construct and <br>
 * deals with all of the registration of materials to that mod; <br>
 * however, you need to integrate these materials in <br>
 * {@link TCAddonIntegration#preInit(FMLPreInitializationEvent)}<br>
 * before they will be recognized by tinkers.
 */
@Pulse(id = TCAddonMaterials.ThisPulseId, description = "All the tool materials added by TCAddonMod", pulsesRequired = TinkerTools.PulseId, forced = true)
public final class TCAddonMaterials {
	
	static final String ThisPulseId = "TCAddonMaterials";
	
	// items added to Tinkers tools
	public static final Material leather = mat("leather", 0x8e661b);
	public static final Material tungsten = mat("tungsten", 0xe6f2ff);
	public static final Material bread = mat("bread", 0x957800);
	public static final Material glass = mat("glass", 0xe7f2ff);
	public static final Material chicken = mat("chicken", 0xf9c0c0);
	public static final Material mushroom = mat("mushroom", 0xff0000);
	
	/** 
	 * Initializes the material. See TinkerMaterials.mat() for initial usage.
	 */
	private static Material mat(String name, int color) {
	    // make materials hidden by default, integration will make them visible if integrated
	    Material mat = new Material(name, color, true);
	    return mat;
	}
	
	/**
	 * Initializes the Stats for the all materials
	 * Stats need to be present before model loading/texture 
	 * generation so we don't generate unneeded parts.
	 * See TinkerMaterials.setupMaterialStats()
	 * for initial usage.
	 */
	@Subscribe
	public static void setupMaterialStats(FMLPreInitializationEvent event) {
		registerToolMaterialStatsAdded();
	}
	
	
	/**
	 * Initializes the all materials for TinkersContruct.
	 * See TinkerMaterials.setupMaterials()
	 * for initial usage.
	 */
	@Subscribe
	public static void setupMaterials(FMLInitializationEvent event) {
		leather.setCraftable(true);
	    leather.addItem("leather", 1, Material.VALUE_Ingot);
	    leather.setRepresentativeItem(new ItemStack(Items.LEATHER));
	    leather.addTrait(TraitsAdded.bovinebane, HEAD);
	    leather.addTrait(TraitsAdded.comfortable);
	    
	    tungsten.setCraftable(true);
	    tungsten.addItem("tungsten", 1, Material.VALUE_Ingot);
	    tungsten.setRepresentativeItem(new ItemStack(ItemInit.TUNGSTEN_INGOT));
	    tungsten.addTrait(TraitsAdded.antiArmor);
	    tungsten.addTrait(TraitsAdded.antiGravity);
	    tungsten.addTrait(TraitsAdded.shout);
	    tungsten.addTrait(TraitsAdded.beam, HEAD);
	    
	    bread.setCraftable(true);
	    bread.addItem("bread", 1, Material.VALUE_Ingot);
	    bread.setRepresentativeItem(new ItemStack(Items.BREAD));
	    bread.addTrait(TraitsAdded.hearty, HEAD);
	    bread.addTrait(TraitsAdded.healthy);
	    
	    glass.setCraftable(true);
	    glass.addItem("glass", 1, Material.VALUE_Ingot);
	    glass.setRepresentativeItem(new ItemStack(Blocks.GLASS));
	    glass.addTrait(TraitsAdded.fragileII, HEAD);
	    glass.addTrait(TraitsAdded.fragile);
	    glass.addTrait(TraitsAdded.shatter, HEAD);
	    
	    chicken.setCraftable(true);
	    chicken.addItem("chicken", 1, Material.VALUE_Ingot);
	    chicken.setRepresentativeItem(new ItemStack(Items.CHICKEN));
	    chicken.addTrait(TraitsAdded.chickenception, HEAD);
	    chicken.addTrait(TraitsAdded.salmonella);
	    chicken.addTrait(TraitsAdded.salmonella, HEAD);
	    
	    mushroom.setCraftable(true);
	    mushroom.addItem("mushroom", 1, Material.VALUE_Ingot);
	    mushroom.setRepresentativeItem(new ItemStack(Blocks.RED_MUSHROOM));
	    mushroom.addTrait(TraitsAdded.shout, HEAD);
	    mushroom.addTrait(TraitsAdded.sanic);
	    mushroom.addTrait(TraitsAdded.sanic, HEAD);
	}
	
	/**
	 * Register all the stats for each tool here. For initial usage, see
	 * TinkersMaterial.registerToolMaterialStats()
	 */
	public static void registerToolMaterialStatsAdded() {
		TinkerRegistry.addMaterialStats(leather,
			new HeadMaterialStats(35, 1.50f, 2.50f, HarvestLevels.STONE),
			new HandleMaterialStats(1.30f, 300),
            new ExtraMaterialStats(150)
        );
		TinkerRegistry.addMaterialStats(tungsten,
			new HeadMaterialStats(1000, .50f, 2.50f, HarvestLevels.IRON),
			new HandleMaterialStats(1.30f, 500),
			new ExtraMaterialStats(300)
		);
		TinkerRegistry.addMaterialStats(bread, 
			new HeadMaterialStats(10, 1.0f, 1.0f, HarvestLevels.STONE),
			new HandleMaterialStats(0.95f, 10),
			new ExtraMaterialStats(10)
		);
		TinkerRegistry.addMaterialStats(glass,
			new HeadMaterialStats(10, 1.0f, 1.0f, HarvestLevels.STONE),
			new HandleMaterialStats(1.0f, 0),
			new ExtraMaterialStats(0)
		);
		TinkerRegistry.addMaterialStats(chicken,
				new HeadMaterialStats(10, 1.0f, 1.0f, HarvestLevels.STONE),
				new HandleMaterialStats(1.0f, 0),
				new ExtraMaterialStats(0)
			);
		TinkerRegistry.addMaterialStats(mushroom,
				new HeadMaterialStats(10, 0.1f, 0.1f, HarvestLevels.STONE),
				new HandleMaterialStats(0.1f, 0),
				new ExtraMaterialStats(0)
			);
		
		
	}
}