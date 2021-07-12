package henryandalex.tinkersaddonmod.items;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.capabilities.inventory.InventoryProvider;
import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.utils.IHasModel;
import henryandalex.tinkersaddonmod.utils.Util;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import slimeknights.mantle.item.ItemArmorTooltip;

public class ItemTotemSatchel extends ItemArmorTooltip implements IHasModel {
	
	// private static final int MAX_TOTEM_STACK = 3; // how many Totems can be carried at once
	public static ArmorMaterial TOTEM_SATCHEL_MATERIAL = EnumHelper.addArmorMaterial("TOTEMSATCHEL", Util.resource("totem_satchel"), 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.BLOCK_SLIME_PLACE, 0);
	
	public ItemTotemSatchel(String name) {
		super(TOTEM_SATCHEL_MATERIAL, 0, EntityEquipmentSlot.CHEST);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		this.setMaxStackSize(1);
		
		ItemInit.ITEMS.add(this);
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		if (!worldIn.isRemote) {
			// This itemStackIn is always a ItemTotemStack so we can suppress this warning.
			@SuppressWarnings("unchecked")
			ItemInventory<ItemTotemSatchel> inv = (ItemInventory<ItemTotemSatchel>) itemStackIn.getCapability(InventoryProvider.INVENTORY_CAP, null);
			if(!inv.updateIsFull()) {
				
				Map<Integer, ItemStack> tempMap = Util.getFromInventory(Items.TOTEM_OF_UNDYING, playerIn.inventory.mainInventory);
				
				for (Map.Entry<Integer, ItemStack> entry : tempMap.entrySet()) {
					if (inv.setTotemInAvailable(entry.getValue())) {
						playerIn.inventory.removeStackFromSlot(entry.getKey());
					}
					else {
						break;
					}
				}
			}
			else {
				inv.clear();
				for(int i = 0; i < inv.getSize(); i++) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items.TOTEM_OF_UNDYING));
				}
			}
		}
	    return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
	}
	
	/**
	 * <p><i><b> Preconditions: Should check if the bag is empty. **Probably not necessary** </b></i></p>
	 * 
	 * <p> 
	 * Should accept the instance of the off hand inventory then append and return it; However, <br>
	 * you can not set the off hand inventory to the returned value. Therefore, you must directly <br>
	 * change it.
	 * </p> 
	 * 
	 * @param player Player whose off hand need to be changed to a totem
	 * @param satchel <i>**Must be a ItemStack containing a Satchel!!**</i>
	 * @return Whether or not the action was a success
	 */
	public boolean setTotem(EntityPlayer player, ItemStack satchel) {
		@SuppressWarnings("unchecked")
		ItemInventory<ItemTotemSatchel> inv = (ItemInventory<ItemTotemSatchel>) satchel.getCapability(InventoryProvider.INVENTORY_CAP, null);
		if(inv.removeSingleStack()) {
			player.inventory.offHandInventory.set(0, new ItemStack(Items.TOTEM_OF_UNDYING));
			return true;
		}
		return false;
	}
	
	@Override
	public boolean getShareTag() {
		return true;
	}
	
	// might be why InventoryStorage#writeNBT is called so much. Maybe get rid of this in the future?
	@Nullable
    @Override
    public NBTTagCompound getNBTShareTag(ItemStack stack) {
        NBTTagCompound tag = super.getNBTShareTag(stack);

        if(tag == null) {
            tag = new NBTTagCompound();
        }

        tag.setTag("Inventory", InventoryProvider.INVENTORY_CAP.writeNBT(stack.getCapability(InventoryProvider.INVENTORY_CAP, null), null));
        
        return tag;
    }
	
	
	@Override
	public void registerModels() {
		TCAddonMod.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
