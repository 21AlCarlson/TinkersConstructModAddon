package henryandalex.tinkersaddonmod.world.gen.village;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.events.RegisterCustomVillageEvent;
import henryandalex.tinkersaddonmod.utils.Util;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.common.MinecraftForge;

/**
 * <p>
 * This class replaces the generic MapGenVillage acting as a sort of wrapper allowing multiple completely custom villages
 * to be added into the game with biome specific spawning. This was a pain in the a-hole to code .-. 
 * </p>
 * <p>
 * This works by checking the biome of the of the world whenever a method is called. It then calls the methods of the 
 * MapGenStructure assigned to that biome. If adding your own biome, check how this mod's custom MapGenStructure classes
 * function (e.i. MapGenWitchsSwampVillage)
 * </p>
 * <p>
 * To register your own biomes, create your own village generation class (which should extend MapGenCustomVillageBase), then
 * subscribe to the
 * {@link henryandalex.tinkersaddonmod.events.RegisterCustomVillageEvent#registerCustomVillage(Biome, MapGenCustomVillageBase)}
 * method passing through in the desired biome and village generation class as parameters. Make sure your custom village class
 * only overrides methods because otherwise they will not automatically get called. 
 * 
 * @author AlexC
 *
 */
public class MapGenVillageHandler extends MapGenVillage {
	
	// Initialized in TerrainGenHandler
	public static MapGenVillageHandler mapGenVillage;
	
	/**
	 * Only need once instance of the MapGen for each type of village so we don't have to worry about storing multiple instances of the same class.
	 */
	private static Map<Biome, MapGenCustomVillageBase> registeredVillages;
	private static MapGenStructure defaultVillage = null;
	
	private static Boolean hasCustomStructureGeneration = false;
	
	public MapGenVillageHandler() {
		super();
		registeredVillages = new HashMap<Biome, MapGenCustomVillageBase>();
		MinecraftForge.EVENT_BUS.post(new RegisterCustomVillageEvent());
	}
		

	/**
	 * Need to be called after {@link #getStructureStart(int, int)}.
	 * This takes the all Village structures in the component list of the respective map gen village class and builds those components.
	 * Called during in Chunk Populate method
	 */
	@Override
	public synchronized boolean generateStructure(World worldIn, Random randomIn, ChunkPos chunkCoord) {
		int x = chunkCoord.getXStart();
		int z = chunkCoord.getZStart();
		
		MapGenCustomVillageBase village = getMapGenStructure(worldIn, new BlockPos(x, 0, z));
		
		if(village == null) {
			if(!hasCustomStructureGeneration) {
				return super.generateStructure(worldIn, randomIn, chunkCoord);
			}
			else {
				return defaultVillage.generateStructure(worldIn, randomIn, chunkCoord);
			}
		}
		
		return village.generateStructure(worldIn, randomIn, chunkCoord);
	}
	
	/*
	 * Called during the Chunk Generate method and results in {@link #getStructureStart(int, int)} being called
	 * No Longer in use as it creates errors in the program. Works perfectly fine without it too :D
	 */
	/*
	@Override
	public void generate(World worldIn, int x, int z, ChunkPrimer primer) {
		if(worldIn.getBiome(new BlockPos(x, 0, z)) instanceof BiomeWitchsSwamp) {
			MAP_GEN_VILLAGE_CUSTOM.generate(worldIn, z, z, primer);
		}
		else {
			super.generate(worldIn, z, z, primer);
		}
		return;
	}
	*/
	

	@Override
	public String getStructureName() {
		if(defaultVillage != null) {
			return "Village";
		}
		return defaultVillage.getStructureName();
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		
		MapGenCustomVillageBase village = getMapGenStructure(worldIn, pos);
		
		if(village == null) {
			if(defaultVillage == null) {
				return super.getNearestStructurePos(worldIn, pos, findUnexplored);
			}
			else {
				return defaultVillage.getNearestStructurePos(worldIn, pos, findUnexplored);
			}
		}
		
		return village.getNearestStructurePos(worldIn, pos, findUnexplored);
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		
		MapGenCustomVillageBase village = getMapGenStructure(this.world, Util.getPosFromChunkCoords(chunkX, chunkZ));
		
		if(village == null) {
			if (defaultVillage != null) {
				// java reflection is used to get the method from defaultVillage instance
				try {
					Field f = MapGenBase.class.getDeclaredField("world");
					boolean fieldInitialAccess = f.isAccessible();
					f.setAccessible(true);
					// when this is first run, the world field in default village is null so we have to initialize it here using reflection.
					if(f.get(defaultVillage) == null) {
						f.set(defaultVillage, this.world);
					}
					f.setAccessible(fieldInitialAccess);
					/*
					Method m;
					//Method[] ms;
					try {
						m = defaultVillage.getClass().getDeclaredMethod("canSpawnStructureAtCoords", int.class, int.class);
					}
					catch(NoSuchMethodException e1) {
						try {
							m = defaultVillage.getClass().getSuperclass().getDeclaredMethod("canSpawnStructureAtCoords", int.class, int.class);
						}
						catch(NullPointerException e2) {
							e2.printStackTrace();
							TCAddonMod.instance.getLogger().warn("Cutom Village from " + defaultVillage.getClass().getName() + " failed to load #canSpawnStructureAtCoords().");
							return super.canSpawnStructureAtCoords(chunkX, chunkZ);
						}
					}
					boolean methodInitialAccess = m.isAccessible();
					m.setAccessible(true);
					boolean val = (boolean) m.invoke(defaultVillage, chunkX, chunkZ);
					m.setAccessible(methodInitialAccess);
					*/
					Method m = Util.getMethod(defaultVillage.getClass(), MapGenStructure.class.getSuperclass(), "canSpawnStructureAtCoords", int.class, int.class);
					Boolean val = Util.runMethod(defaultVillage, boolean.class, m, chunkX, chunkZ);
					return val;
				} 
				catch(NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException e3) {
					e3.printStackTrace();
					TCAddonMod.instance.getLogger().warn("Cutom Village from " + defaultVillage.getClass().getName() + " failed to load #canSpawnStructureAtCoords().");
				}
			}
			return super.canSpawnStructureAtCoords(chunkX, chunkZ);
		}
		return village.canSpawnStructureAtCoordsPublic(this.world, chunkX, chunkZ);
	}

	/**
	 * Adds the components (houses, paths, wells, etc) to the village instance. Does not actually build them.
	 * Called during the Chunk Generate method as a part of {@link #generate(World, int, int, ChunkPrimer)}
	 */
	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		
		MapGenCustomVillageBase village = getMapGenStructure(this.world, Util.getPosFromChunkCoords(chunkX, chunkZ));
		
		if(village == null) {
			if(defaultVillage != null) {
				// java reflection is used to get the method from defaultVillage instance
				try {
					Method m = Util.getMethod(defaultVillage.getClass(), MapGenStructure.class.getSuperclass(), "getStructureStart", int.class, int.class);
					StructureStart village1 = Util.runMethod(defaultVillage, StructureStart.class, m, chunkX, chunkZ);
					
					// If it has a custom generation, then we need to save the structure start to defaultVillage's structureMap.
					if(hasCustomStructureGeneration) {
						Field f = MapGenStructure.class.getDeclaredField("structureMap");
						boolean accessible = f.isAccessible();
						f.setAccessible(true);
						@SuppressWarnings("unchecked")
						Long2ObjectMap<StructureStart> newStructureMap = (Long2ObjectMap<StructureStart>) f.get(defaultVillage);
						newStructureMap.put(ChunkPos.asLong(chunkX, chunkZ), village1);
						f.set(defaultVillage, newStructureMap);
						f.setAccessible(accessible); 
					}
					
					TCAddonMod.instance.getLogger().info("New Vanilla Village @ (Chunk x: " + chunkX + ", Chunk z: " + chunkZ + ")");
			        BlockPos pos = Util.getPosFromChunkCoords(chunkX, chunkZ);
			        TCAddonMod.instance.getLogger().info("New Vanilla Village @ (x: " + pos.getX() + ", z: " + pos.getZ() + ")");
					
					return village1;
				}
				catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
					e.printStackTrace();
					TCAddonMod.instance.getLogger().warn("Cutom Village from " + defaultVillage.getClass().getName() + " failed to load #getStrucutreStart().");
				}
			}
			//TODO: remove coord printing in final release.
	        TCAddonMod.instance.getLogger().info("Vanilla Village @ (Chunk x: " + chunkX + ", Chunk z: " + chunkZ + ")");
	        BlockPos pos = Util.getPosFromChunkCoords(chunkX, chunkZ);
	        TCAddonMod.instance.getLogger().info("Vanilla Village @ (x: " + pos.getX() + ", z: " + pos.getZ() + ")");
	        
			return super.getStructureStart(chunkX, chunkZ);
		}
		
        //TODO: remove coord printing in final release
        TCAddonMod.instance.getLogger().info("Custom Village @ (Chunk x: " + chunkX + ", Chunk z: " + chunkZ + ")");
        BlockPos pos = Util.getPosFromChunkCoords(chunkX, chunkZ);
        TCAddonMod.instance.getLogger().info("Custom Village @ (x: " + pos.getX() + ", z: " + pos.getZ() + ")");
		
		village.getStructureStartPublic(chunkX, chunkZ);
		
		/*
		 * You have to use 'MapGenVillage.Start' and not 'new StructureStart' because StructureStart is not
		 * registered with the MapGenStructureIO.
		 */
		return new MapGenVillage.Start() {
			@Override 
		    public boolean isValidForPostProcess(ChunkPos pair) {
				/*
				 * This ensures that a village wont be built with this instance of StructureStart 
				 * and consequently the game wont break when super#generateStructure() is called in
				 * this#generateStructure(). This needs to be declared because this StructureStart
				 * is going to be registered to this object's instance of structureMap (which there 
				 * is no way around unless I use an access transformer to remove the final keyword 
				 * for MapGenStructure#recursiveGenerate), and we don't want it to try to build a 
				 * village with this empty instance (chunkPosX and chunkPosZ have not been initialized
				 * in this instance which should theoretically cause bugs but I am too lazy to test 
				 * if it actually does).
				 */
		        return false;
		    }
		};
	}
	
	/**
	 * Gets the Biome specific MapGenVillage
	 */
	private MapGenCustomVillageBase getMapGenStructure(World world, BlockPos pos) {
		for(Entry<Biome, MapGenCustomVillageBase> entry : registeredVillages.entrySet()) {
			if (world.getBiome(pos).getClass().equals(entry.getKey().getClass())) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	private static <T extends MapGenCustomVillageBase> void registerVillage(Biome biome, T villageGenerator) {
		if(!registeredVillages.containsKey(biome)) {
			registeredVillages.put(biome, villageGenerator);
		}
	}
	
	/**
	 * <tt> Never call this method! Subscribe to this bus instead:
	 * {@link RegisterCustomVillageEvent#registerCustomVillage(MapGenCustomVillageBase)}
	 */
	public static <T extends MapGenCustomVillageBase> void registerVillage(T villageGenerator) {
		
		List<Biome> villageBiomes = villageGenerator.getVillageSpawnBiomes();
		
		for(Biome biome : villageBiomes) {
			registerVillage(biome, villageGenerator);
		}
	}
	
	/**
	 * Do no use this method!! This mod only uses this method when the TerrianGenHandler overrides the previous
	 * overworld's village generator with an instance of this class.
	 */
	//TODO: Change MapGenStructure back to MapGenVillage
	public static <T extends MapGenStructure> void registerNewVanillaVillage(T villageGenerator) {
		// need to convert MapGenStructure to the MapGenVillageBase
		defaultVillage = villageGenerator;
		
		/*
		 * If this village generator has a custom generation method, then we will need to handle it differently
		 * because of how the stuctureMap works in MapGenStructure.
		 */
		try {
			hasCustomStructureGeneration = 	MapGenStructure.class.getMethod("generateStructure", World.class, Random.class, ChunkPos.class) ==
				defaultVillage.getClass().getMethod("generateStructure", World.class, Random.class, ChunkPos.class);
		
		}
		catch(NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
