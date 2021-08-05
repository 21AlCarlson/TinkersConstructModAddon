package henryandalex.tinkersaddonmod.world.gen.witchsvillage;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

// TODO: remove this class because it adds the witch's village houses to regular villages.
public class WitchsVillageHouseCreationHandler implements IVillageCreationHandler {

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) {
		return new PieceWeight(getComponentClass(), 5, 0);
	}

	@Override
	public Class<? extends Village> getComponentClass() {
		return StructureWitchsVillagePieces.House.class;
	}

	@Override
	public Village buildComponent(PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces,
			Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
        return StructureWitchsVillagePieces.House.createPiece(startPiece, pieces, random, p1, p2, p3, facing, p5);
    }
}

