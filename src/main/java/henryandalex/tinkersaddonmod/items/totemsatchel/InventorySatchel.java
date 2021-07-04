package henryandalex.tinkersaddonmod.items.totemsatchel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Referenced {@link net.minecraft.entity.player.InventoryPlayer}
 * 
 * @author AlexC
 *
 */
public class InventorySatchel implements IInventory {

	public static final int SIZE = 3;
	private int timesChanged;
	public final NonNullList<ItemStack> inv;
	private boolean isFull;
	private static final int nbtIndex = 0;
	
	InventorySatchel() {
		this.timesChanged = 0;
		this.isFull = false;
		this.inv = NonNullList.<ItemStack>withSize(SIZE, ItemStack.EMPTY);
	}
	
	@Override
	public String getName() {
		return "container.inventory";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
	}

	@Override
	public int getSizeInventory() {
		return SIZE;
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack item : inv) {
			if(!item.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inv.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		updateIsFull();
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		setInventorySlotContents(index, ItemStack.EMPTY);
		return inv.get(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inv.set(index, stack);
		updateIsFull();
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (inv.get(index).isEmpty() && stack.isItemEqual(new ItemStack(Items.TOTEM_OF_UNDYING))) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		for(int i = 0; i < SIZE; i++) {
			this.removeStackFromSlot(i);
		}
	}

	public boolean setTotemInAvailable(ItemStack totem) {
		for (int index = 0; index < SIZE; index++) {
			if(isItemValidForSlot(index, totem)) {
				this.setInventorySlotContents(index, totem);
				return true;
			}
		}
		return false;
	}
	
	public boolean updateIsFull() {
		for (ItemStack itemstack : inv) {
			if (itemstack.isEmpty()) {
				isFull = false;
				return isFull;
			}
		}
		isFull = true;
		return isFull;
	}

	public boolean removeSingleStack() {
		int i = 0;
		for (ItemStack stack : inv) {
			if (!stack.isEmpty()) {
				this.removeStackFromSlot(i);
				return true;
			}
			i++;
		}
		return false;
	}
	
	public NBTTagList writeToNBT(NBTTagList nbtTagListIn) {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		int numOfItemsInInv = 0;
		for (ItemStack stack : inv) {
			if(stack.isItemEqual(new ItemStack(Items.TOTEM_OF_UNDYING))) numOfItemsInInv++;
		}
		nbttagcompound.setInteger("itemsInInv", numOfItemsInInv);
		nbtTagListIn.set(nbtIndex, nbttagcompound);
		return nbtTagListIn;
	}
	
	public void readFromNBT(NBTTagList nbtTagListIn) {
		this.clear();
		
		NBTTagCompound nbttagcompound = nbtTagListIn.getCompoundTagAt(nbtIndex);
		int itemsInInv = nbttagcompound.getInteger("itemsInInv");
		if (itemsInInv != 0)
		for (int i = 0; i < itemsInInv; i++) {
			inv.set(i, new ItemStack(Items.TOTEM_OF_UNDYING));
		}
		updateIsFull();
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void markDirty() {
		++this.timesChanged;
	}

    @SideOnly(Side.CLIENT)
    public int getTimesChanged() {
        return this.timesChanged;
    }
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		return;
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		return;
	}
	
	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}
}
