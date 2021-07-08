package henryandalex.tinkersaddonmod.capabilities.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface ISaveInventorySpaces {
	
	public NonNullList<ItemStack> getInventory();
	
	public void setInventory(NonNullList<ItemStack> inv);
	
	public int getSize();
}
