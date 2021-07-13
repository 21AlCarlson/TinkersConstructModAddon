package henryandalex.tinkersaddonmod.capabilities.inventories;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class InventoryProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(ISaveInventorySpaces.class)
	public static final Capability<ISaveInventorySpaces> INVENTORY_CAP = null;
	
	private ISaveInventorySpaces instance = INVENTORY_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == INVENTORY_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			return capability == INVENTORY_CAP ? INVENTORY_CAP.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return INVENTORY_CAP.getStorage().writeNBT(INVENTORY_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		INVENTORY_CAP.getStorage().readNBT(INVENTORY_CAP, this.instance , null, nbt);
	}
}
