package henryandalex.tinkersaddonmod.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

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
}
