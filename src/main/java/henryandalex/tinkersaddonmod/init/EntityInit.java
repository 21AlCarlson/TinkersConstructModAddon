package henryandalex.tinkersaddonmod.init;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.entity.EntityBeam;
import henryandalex.tinkersaddonmod.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
	
	public static void registerEntities() {
		registerProjectile("beam", Reference.ENTITY_BEAM, EntityBeam.class, ItemInit.BEAM);
	}
	
	public static void registerProjectile(String name, int id, Class <? extends Entity> entity, Item item) {
		EntityRegistry.registerModEntity(new ResourceLocation(name), entity, name, id, TCAddonMod.instance, 64, 10, true);
	}
	
	

}
