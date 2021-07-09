package henryandalex.tinkersaddonmod.init;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.entity.EntityBeam;
import henryandalex.tinkersaddonmod.entity.EntitySewerCroc;
import henryandalex.tinkersaddonmod.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
	
	public static void registerEntityLiving(String name, Class<? extends EntityLiving> entity, int id, int range, int color1, int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, TCAddonMod.instance, range, 1, true, color1, color2);	
	}
	
	public static void registerEntities() {
		registerProjectile("beam", Reference.ENTITY_BEAM, EntityBeam.class, ItemInit.BEAM);
		
	}
	
	public static void registerLivingEntities() {
		registerEntityLiving("sewer_croc", EntitySewerCroc.class, Reference.ENTITY_SEWER_CROC, 50, 0x53713A, 0x3E2F5C);
	}
	
	public static void registerProjectile(String name, int id, Class <? extends Entity> entity, Item item) {
		EntityRegistry.registerModEntity(new ResourceLocation(name), entity, name, id, TCAddonMod.instance, 64, 10, true);
	}
	
	

}
