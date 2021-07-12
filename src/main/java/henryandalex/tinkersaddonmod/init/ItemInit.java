package henryandalex.tinkersaddonmod.init;

import java.util.ArrayList;
import java.util.List;

import henryandalex.tinkersaddonmod.items.ItemBase;
import henryandalex.tinkersaddonmod.items.ItemTotemSatchel;
import net.minecraft.item.Item;

/**
 * This contains all a list of all the items 
 * and their respective static, final variables
 * 
 * @author AlexC
 *
 */
public class ItemInit {
	// List of all the items this mod adds to the game
	// Does not contain the items already in the game but not registered to tinkers 
	// e.i. Leather is not in this list
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	// declaration of the Tungsten Ingot
	public static final Item TUNGSTEN_INGOT = new ItemBase("ingot_tungsten");
	
	public static final Item BEAM = new ItemBase("beam");
	
	public static final Item TOTEM_SATCHEL = new ItemTotemSatchel("totem_satchel");
	
	public static final Item MASTER_INGOT = new ItemBase("ingot_master");
	
	public static final Item SUPERIOR_STEEL_INGOT = new ItemBase("ingot_superior_steel");
}
