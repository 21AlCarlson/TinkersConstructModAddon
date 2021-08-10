package henryandalex.tinkersaddonmod.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nullable;

import henryandalex.tinkersaddonmod.TCAddonMod;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

/**
 * Has just a bunch of different helper functions that can be useful in more than one case.
 * 
 * Testing seed (Witch's Village): 5082801505840576449
 * Testing seed (Vanilla Village): 113422530337198667
 * 
 * @author AlexC
 *
 */
public class Util {
	
	public static final String RESOURCE = Reference.MODID;
	
	public static String res(String domain, String path) {
		return String.format("%s:%s", domain, path);
	}
	
	/**
	 * Returns the given Resource prefixed with tinkers resource location. Use this function instead of hardcoding
	 * resource locations.
	 * {@link slimeknights.tconstruct.library.Util#resource(String)}
	 */
	public static String res(String path) {
		return res(RESOURCE, path);
	}
	
	/**
	 * {@link slimeknights.tconstruct.library.Util#prefix(String)}
	 */
	public static String prefix(String name) {
		return String.format("%s.%s", RESOURCE, name.toLowerCase(Locale.US));
	}
	
	/**
	 * {@link henryandalex.tinkersaddonmod.utils.Util#getFromInventory(ItemStack, NonNullList, int)}
	 */
	@Nullable
	public static Map<Integer, ItemStack> getFromInventory(Item item, NonNullList<ItemStack> inventory) {
		return getFromInventory(new ItemStack(item), inventory, 0);
	}
	
	/**
	 * {@link henryandalex.tinkersaddonmod.utils.Util#getFromInventory(ItemStack, NonNullList, int)}
	 */
	@Nullable 
	public static Map<Integer, ItemStack> getFromInventory(ItemStack itemstack, NonNullList<ItemStack> inventory) {
		return getFromInventory(itemstack, inventory, 0);
	}
		
	/**
	 * Maps all of the inventory spaces with itemstackcom and returns them with their inventory slots.
	 * 
	 * @param itemstackcom Item to check against
	 * @param inventory Inventory to check (e.i. inventory.mainInventory)
	 * @param shift Shift in the returned Map. Used when stringing multiple maps from multiple inventories.
	 * @return All the instances of itemstackcom in the inventory
	 * mapped with their position in that inventory. This could be null
	 */
	@Nullable
	public static Map<Integer, ItemStack> getFromInventory(ItemStack itemstackcom, NonNullList<ItemStack> inventory, int shift) {
		
		Map<Integer, ItemStack> returnMap = new HashMap<Integer, ItemStack>();
		
		/**
		 * {@link net.minecraft.entity.player.InventoryPlayer#hasItemStack()
		 */
			
		Iterator<ItemStack> iterator = inventory.iterator();

		int i = 0;
		
        while (true) {
            if (!iterator.hasNext()) {
                break;
            }

            ItemStack itemstack = (ItemStack)iterator.next();

            if (!itemstack.isEmpty() && itemstack.isItemEqual(itemstackcom)) {
            	returnMap.put(i + shift, itemstack);
            }
            i++;
        }
		return returnMap;
	}
	
	/**
	 * {@link henryandalex.tinkersaddonmod.utils.Util#getFromAllPlayerInventories(ItemStack, InventoryPlayer)}
	 */
	public static Map<Integer, ItemStack> getFromAllPlayerInventories(Item item, InventoryPlayer inventory) {
		return getFromAllPlayerInventories(new ItemStack(item), inventory);
	}
	
	/**
	 * Checks all the inventories a player has (main, armor, and off hand) for the given item.
	 * 
	 * @param itemstackcom Item to check
	 * @param inventory Instance of InventoryPlayer to check
	 * @return All the instances of itemstackcom in the inventory
	 * mapped with their position in that inventory. This could be null
	 */
	@Nullable
	public static Map<Integer, ItemStack> getFromAllPlayerInventories(ItemStack itemstackcom, InventoryPlayer inventory) {
		
		Map<Integer, ItemStack> returnMap = new HashMap<Integer, ItemStack>();
		
		// order of which inventories (main versus armor etc.) you check for the item matters! 
		// Has to match the initialization of allInventories in the InventoryPlayer constructor.
		try {
			returnMap.putAll(getFromInventory(itemstackcom, inventory.mainInventory));
		}
		catch(NullPointerException e) {} 
		
		try {
			returnMap.putAll(getFromInventory(itemstackcom, inventory.armorInventory, inventory.mainInventory.size()));
		}
		catch(NullPointerException ee) {}
		
		try {
			returnMap.putAll(getFromInventory(itemstackcom, inventory.offHandInventory, inventory.mainInventory.size() + inventory.offHandInventory.size()));
		}
		catch(NullPointerException eee) {}
		
		return returnMap;
	}

	public static <T extends Biome> boolean isBiomeNearby(BlockPos blockPos, T biome, World world) {
		return true;
	}

	public static BlockPos getPosFromChunkCoords(int chunkX, int chunkZ) {
		return new BlockPos(chunkX * 16, 0, chunkZ * 16);
	}
	
	public static Biome getBiomefromChunkCoords(World world, int chunkX, int chunkZ) {
		return world.getBiome(getPosFromChunkCoords(chunkX, chunkZ));
	}
	
	/** Used for saving data */
	public static long concat(int x, int z) {
		// shift by 32 bits move the length of the integer
		return (x << 32) + z;
	}
	
	/** 
	 * Gets a method instance from the given class. Checks all the super classes too.
	 * 
	 * @param clazz Class to check
	 * @param clazz2StopAt  Class at which the code will stop checking the methods for (exclusive) <br>
	 * 						Use null if you don't want it to stop at a class.
	 * @param methodName Name of the method you want to get
	 * @param parameterTypes The types of parameters of the method takes in
	 * @return
	 */
	public static Method getMethod(Class<?> clazz, Class<?> clazz2StopAt, String methodName, Class<?>... parameterTypes) {
		while(true) {
			try {
				Method m = clazz.getDeclaredMethod(methodName, parameterTypes);
				return m;
			} 
			catch(NoSuchMethodException e) {
				clazz = clazz.getSuperclass();
				if(clazz.equals(clazz2StopAt)) break;
				else continue;
			}
			catch(SecurityException e) {
				e.printStackTrace();
				break;
			}
		}
		return null;
	}
	
	/**
	 * This will run a method whether or not it is accessible by the class or not.
	 * 
	 * @param m Method to run
	 * @param retrunType The class of the object to be returned. Set as null if the method is void.
	 * @param instance Instance to run the method on
	 * @param args Arguments for the method
	 * @return The object that the 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T runMethod(Object instance, Class<T> retrunType, Method m, Object... args) {
		T returnObject = null;
		Boolean accessible = m.isAccessible();
		m.setAccessible(true);
		try {
			if(retrunType != null) {
				returnObject = (T) m.invoke(instance, args);
			}
			else {
				m.invoke(instance, args); 
			}
		}
		catch(InvocationTargetException e) {
			e.printStackTrace();
			TCAddonMod.instance.getLogger().warn("Method threw an error when trying to call it during relfection.");
		}
		catch(IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		m.setAccessible(accessible);
		return returnObject;
	}
}
