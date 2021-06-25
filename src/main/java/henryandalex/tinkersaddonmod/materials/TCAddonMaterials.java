package henryandalex.tinkersaddonmod.materials;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;

import static slimeknights.tconstruct.library.utils.HarvestLevels.STONE;

import static henryandalex.tinkersaddonmod.traits.TraitsAdded.comfortable;

//@Pulse(id = TCAddonMaterials.PulseId, description = "All the tool materials added by TCAddonMod", pulsesRequired = TinkerTools.PulseId, forced = true)
public final class TCAddonMaterials {
	
	//static final String PulseId = "TCAddonMaterials";
	
	// items added to Tinkers tools
	public static final Material leather = mat("leather", 0x8e661b);
	
	
	public static final List<Material> materialsAdded = Lists.newArrayList();
	
	private static Material mat(String name, int color) {
	    // make materials hidden by default, integration will make them visible if integrated
	    Material mat = new Material(name, color, true);
	    //materialsAdded.add(mat);
	    return mat;
	}
	
	//Should be a subscribe event as seen in the TinkerMaterials class; however, it is not working :/
	//My fix? Use a method I know is working :D (see preInit in TCAddonMod)
	public static void setupMaterialStats(FMLPreInitializationEvent event) {
		// stats need to be present before model loading/texture generation so we don't generate unneeded parts
		registerToolMaterialStatsAdded();
	}
	
	public static void preInit(FMLPreInitializationEvent event) {
		setupMaterialStats(event);
	}
	
	
	//Should be a subscribe event as seen in the TinkerMaterials class; however, it is not working :/
	//My fix? Use a method I know is working :D (see init in TCAddonMod)
	public static void setupMaterials(FMLInitializationEvent event) {
		leather.setCraftable(true);
	    leather.addItem("leather", 1, Material.VALUE_Ingot);
	    leather.setRepresentativeItem(new ItemStack(Items.LEATHER));
	    //leather.addTrait(bovinebane, HEAD);
	    leather.addTrait(comfortable);
	}
	
	public static void init(FMLInitializationEvent event) {
		setupMaterials(event);
	}
	
	public static void registerToolMaterialStatsAdded() {
		TinkerRegistry.addMaterialStats(leather,
				new HeadMaterialStats(35, 1.50f, 2.50f, STONE),
				new HandleMaterialStats(1.30f, 300),
                new ExtraMaterialStats(150));
	}
}
