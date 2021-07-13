package henryandalex.tinkersaddonmod.items;

import henryandalex.tinkersaddonmod.capabilities.inventories.ISaveInventorySpaces;
import henryandalex.tinkersaddonmod.utils.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * <b>**Must Initialize before usage!!**</b>
 * 
 * 
 * Single type inventory
 * 
 * Referenced {@link net.minecraft.entity.player.InventoryPlayer}
 * 
 * @author AlexC
 *
 */
public class ItemInventory implements IInventory, ISaveInventorySpaces {

	public final int SIZE;
	public static final int DEFAULT_SIZE = 3;
	private int timesChanged;
	public NonNullList<ItemStack> inv;
	private boolean isFull;
	private Item itemTypeInInv;
	
	public ItemInventory(int size) {
		this.SIZE = size;
		this.timesChanged = 0;
		this.isFull = false;
		this.inv = NonNullList.<ItemStack>withSize(SIZE, ItemStack.EMPTY);
	}
	
	public ItemInventory() {
		this(DEFAULT_SIZE);
	}
	
	@Override
	public <T extends Item> void initInvSpaceType(T t) {
		this.itemTypeInInv = t;
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
	public int getSize() {
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

	@Deprecated
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
		if (inv.get(index).isEmpty() && stack.isItemEqual(new ItemStack(
				// This should be done to get the registered instance of the item class. Probably not necessary but safer.
				Item.getByNameOrId(Util.resource(this.itemTypeInInv.getRegistryName().getResourceDomain(), this.itemTypeInInv.getRegistryName().getResourcePath()))
			))) {
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

	public boolean setItemInAvailable(ItemStack stack) {
		for (int index = 0; index < SIZE; index++) {
			if(this.isItemValidForSlot(index, stack)) {
				this.setInventorySlotContents(index, stack);
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
	
	@Override
	public NonNullList<ItemStack> getInventory() {
		return this.inv;
	}

	@Override
	public void setInventory(NonNullList<ItemStack> inv) {
		this.inv = inv;
	}
	
	/***********************************/
	
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
