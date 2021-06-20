package henryandalex.tinkersaddonmod.materials;

import static slimeknights.tconstruct.library.utils.HarvestLevels.STONE;
import static slimeknights.tconstruct.tools.TinkerTraits.ecological;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.tools.TinkerTools;

import static slimeknights.tconstruct.library.materials.MaterialTypes.HEAD;
import static henryandalex.tinkersaddonmod.traits.TraitsAdded.bovinebane;
import static henryandalex.tinkersaddonmod.traits.TraitsAdded.comfortable;

public final class Materials {
	
	
	
	public static final Material leather = mat("leather", 0x8e661b);
	
	
	public static final List<Material> materialsAdded = Lists.newArrayList();
	
	private static Material mat(String name, int color) {
	    // make materials hidden by default, integration will make them visible if integrated
	    Material mat = new Material(name, color, true);
	    materialsAdded.add(mat);
	    return mat;
	  }
	
	@Subscribe
	  public void setupMaterialStats(FMLPreInitializationEvent event) {
	    // stats need to be present before model loading/texture generation so we don't generate unneeded parts
	    registerToolMaterialStatsAdded();

	  }
	
	
	@Subscribe
	public void setupMaterialsAdded(FMLInitializationEvent event) {
		leather.setCraftable(true);
	    leather.addItem("leather", 1, Material.VALUE_Ingot);
	    leather.setRepresentativeItem(new ItemStack(Items.LEATHER));
	    leather.addTrait(bovinebane, HEAD);
	    leather.addTrait(comfortable);
	}
	
	
	public void registerToolMaterialStatsAdded() {
		TinkerRegistry.addMaterialStats(leather,
                new HeadMaterialStats(35, 1.50f, 2.50f, STONE),
                new HandleMaterialStats(1.30f, 300),
                new ExtraMaterialStats(150));
	}
	

}
