package henryandalex.tinkersaddonmod.capabilities.inventories;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface ISaveInventorySpaces {
	
	public NonNullList<ItemStack> getInventory();
	
	public void setInventory(NonNullList<ItemStack> inv);
	
	public int getSize();

	public <T extends Item> void initInvSpaceType(T t);
}
