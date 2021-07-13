package henryandalex.tinkersaddonmod.capabilities.inventories;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class InventoryStorage implements IStorage<ISaveInventorySpaces> {
	
	@Override
	public NBTBase writeNBT(Capability<ISaveInventorySpaces> capability, ISaveInventorySpaces instance,
			EnumFacing side) {
		
		NonNullList<ItemStack> inv = instance.getInventory();
		
		NBTTagList nbtTagListOut = new NBTTagList();
		
		// Boolean "Empty" Defines whether or not that itemstack is empty
		for (ItemStack stack : inv) {
			NBTTagCompound nbt = stack.writeToNBT(new NBTTagCompound());
			if (nbt != null) {
				nbt.setBoolean("Empty", false);
				nbtTagListOut.appendTag(nbt);
			}
			else if (stack.isEmpty()) {
				NBTTagCompound nbtNew = new NBTTagCompound();
				nbtNew.setBoolean("Empty", true);
				nbtTagListOut.appendTag(nbt);
			}
			else {
				NBTTagCompound nbtNew = new NBTTagCompound();
				nbtNew.setBoolean("Empty", false);
				nbtTagListOut.appendTag(nbt);
			}
		}
		
		return nbtTagListOut;
	}

	@Override
	public void readNBT(Capability<ISaveInventorySpaces> capability, ISaveInventorySpaces instance, EnumFacing side,
			NBTBase nbt) {
		
		NBTTagList nbtTagListIn = (NBTTagList) nbt;
		// save to items to temp list first is a little bit safer
		List<ItemStack> temp = new ArrayList<ItemStack>();
		NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(instance.getSize(), ItemStack.EMPTY);
	
		nbtTagListIn.forEach((nbtTagCompound) -> {
			temp.add(new ItemStack((NBTTagCompound) nbtTagCompound));
		});
		
		// Use inv.size because this will cut off any extra data
		for (int i = 0; i < inv.size(); i++) {
			try {
				 inv.set(i, temp.get(i));
			}
			catch(IndexOutOfBoundsException  e) {
				inv.set(i, ItemStack.EMPTY);
			}
		}
		instance.setInventory(inv);
	}
}
