package henryandalex.tinkersaddonmod.world.gen.structure.witchsvillage;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class WitchsVillageHouse extends StructureVillagePieces.Village {

	public WitchsVillageHouse() {}

	public WitchsVillageHouse(StructureVillagePieces.Start parStart, int parType, Random parRand, StructureBoundingBox parStructBB, EnumFacing parFacing) {
		super(parStart, parType);
		setCoordBaseMode(parFacing);
		boundingBox = parStructBB;
	}
	
	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		
		return false;
	}
}
