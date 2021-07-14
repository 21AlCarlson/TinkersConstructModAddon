package henryandalex.tinkersaddonmod.utils.handlers;

import henryandalex.tinkersaddonmod.init.BiomeInit;
import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.utils.IHasModel;
import henryandalex.tinkersaddonmod.utils.Util;
import henryandalex.tinkersaddonmod.world.feature.tree.WitchsWoodTreeGen;
import henryandalex.tinkersaddonmod.world.gen.WorldGenCustomOres;
import henryandalex.tinkersaddonmod.world.gen.structure.witchsvillage.WitchsVillageHouse;
import henryandalex.tinkersaddonmod.world.gen.structure.witchsvillage.WitchsVillageHouseCreationHandler;
import slimeknights.tconstruct.common.TinkerOredict;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

/**
 * This is where everything gets registered - items, blocks, <br>
 * and models - with the predefined methods in each class.
 */
@EventBusSubscriber
public class RegistryHandler {
	
	/**
	 * Registers all the blocks and item oredicts.
	 * Note that it's using the item registry event, since it's called after blocks.
	 * This relies on the TinkerOredict pulse being called after the pulses registering the items
	 * See {@link TinerOredict} for initial usage.
	 */
	@SubscribeEvent
	public static void registerItemsToTinkers(RegistryEvent.Register<Item> event) {
		registerCommon();
	}
	
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item : ItemInit.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}
		
		for (Block block : BlockInit.BLOCKS) {
			if (block instanceof IHasModel) {
				((IHasModel) block).registerModels();
			}
		}
	}
	
	public static void otherRegistries() {
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
		GameRegistry.registerWorldGenerator(new WitchsWoodTreeGen(), 0);
		
		BiomeInit.registerBiomes();
		
		
	}
	
	public static void registerEventListeners() {
		MinecraftForge.TERRAIN_GEN_BUS.register(TerrainGenHandler.class);
	}
	
	
	private static void registerCommon() {
		TinkerOredict.oredict(Items.LEATHER, "leather");
		TinkerOredict.oredict(ItemInit.TUNGSTEN_INGOT, "tungsten");
		TinkerOredict.oredict(Blocks.GLASS, "glass");
		TinkerOredict.oredict(Items.CHICKEN, "chicken");
		TinkerOredict.oredict(Items.BREAD, "bread");
		TinkerOredict.oredict(Blocks.RED_MUSHROOM, "mushroom");
		TinkerOredict.oredict(BlockInit.BLOCK_TUNGSTEN, "tungsten_block");
		TinkerOredict.oredict(ItemInit.MASTER_INGOT, "master");
	}


	public static void registerCustomVillage() {
		MapGenStructureIO.registerStructureComponent(WitchsVillageHouse.class, Util.res("village_house"));
		VillagerRegistry.instance().registerVillageCreationHandler((IVillageCreationHandler) new WitchsVillageHouseCreationHandler());
	}
}
