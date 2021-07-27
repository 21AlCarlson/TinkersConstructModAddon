package henryandalex.tinkersaddonmod.fluids;

import henryandalex.tinkersaddonmod.materials.TCAddonMaterials;
import henryandalex.tinkersaddonmod.utils.Util;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import slimeknights.mantle.pulsar.pulse.Pulse;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

@Pulse(id = TCAddonFluids.PulseId, pulsesRequired = TinkerSmeltery.PulseId, forced = true)
public class TCAddonFluids {
	
	public static final String PulseId = "TCAddonFluids";

	public static FluidMolten tungsten;
	
	static {
		setupFluids();
	}
	
	public static void setupFluids() {
		tungsten = fluidMetal(TCAddonMaterials.tungsten);
		// set as one for testing
		tungsten.setTemperature(1);
	}
	
	private static FluidMolten fluidMetal(Material material) {
		return fluidMetal(material.getIdentifier(), material.materialTextColor);
	}
	
	private static FluidMolten fluidMetal(String name, int color) {
		FluidMolten fluid = new FluidMolten(name, color);
		return registerFluid(fluid);
	}
	
	protected static <T extends Fluid> T registerFluid(T fluid) {
		fluid.setUnlocalizedName(Util.prefix(fluid.getName()));
		FluidRegistry.registerFluid(fluid);

		return fluid;
	}
	
	protected static boolean isSmelteryLoaded() {
		return TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId);
	}
}
