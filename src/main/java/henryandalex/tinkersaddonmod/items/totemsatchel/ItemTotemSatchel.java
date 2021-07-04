package henryandalex.tinkersaddonmod.items.totemsatchel;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnull;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.init.ItemInit;
import henryandalex.tinkersaddonmod.utils.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import slimeknights.mantle.item.ItemArmorTooltip;
import slimeknights.tconstruct.library.Util;

public class ItemTotemSatchel extends ItemArmorTooltip implements IHasModel {
	
	// private static final int MAX_TOTEM_STACK = 3; // how many Totems can be carried at once
	public static ArmorMaterial TOTEM_SATCHEL_MATERIAL = EnumHelper.addArmorMaterial("TOTEMSATCHEL", Util.resource("totem_satchel"), 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.BLOCK_SLIME_PLACE, 0);
	public InventorySatchel inv;

	public ItemTotemSatchel(String name) {
		super(TOTEM_SATCHEL_MATERIAL, 0, EntityEquipmentSlot.CHEST);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		this.setMaxStackSize(1);
		this.inv = new InventorySatchel();
		
		ItemInit.ITEMS.add(this);
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		if (!worldIn.isRemote) {
			if(!this.inv.updateIsFull()) {
				/**
				 * {@link net.minecraft.entity.player.InventoryPlayer#hasItemStack()
				 */
				List<NonNullList<ItemStack>> allInventories = Arrays.<NonNullList<ItemStack>>asList(playerIn.inventory.mainInventory);
				
				label23:
					
				for (List<ItemStack> list : allInventories) {
					
					Iterator<ItemStack> iterator = list.iterator();
		
		            while (true) {
		                if (!iterator.hasNext()) {
		                    continue label23;
		                }
		
		                ItemStack itemstack = (ItemStack)iterator.next();
		
		                if (!itemstack.isEmpty() && itemstack.isItemEqual(new ItemStack(Items.TOTEM_OF_UNDYING))) {
		                	if(!this.inv.setTotemInAvailable(itemstack)) {
		                		// if you can not setTotem in bag it, that means the bag is full 
		                		// so we dont need to check any more.
		                		break label23;
		                	}
		                	else {
		                		playerIn.inventory.removeStackFromSlot(playerIn.inventory.getSlotFor(itemstack));
		                	}
		                }
		            }
				}
			}
			else {
				this.inv.clear();
				for(int i = 0; i < InventorySatchel.SIZE; i++) {
					playerIn.inventory.addItemStackToInventory(new ItemStack(Items.TOTEM_OF_UNDYING));
				}
			}
		}
	    return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
	}
	
	public void setTotem(EntityPlayer player) {
		if(!this.inv.isEmpty()) {
			player.inventory.offHandInventory.set(0, new ItemStack(Items.TOTEM_OF_UNDYING));
			this.inv.removeSingleStack();
		}
	}
	
	@Override
	public void registerModels() {
		TCAddonMod.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
