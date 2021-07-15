package henryandalex.tinkersaddonmod.utils.handlers;
/*
import java.util.Random;

import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.utils.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRiver;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent.ReplaceBiomeBlocks;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@EventBusSubscriber
public class EventHandler {
	
	
	@SubscribeEvent
	public static <T extends Biome> void replaceBlocksEvent(ReplaceBiomeBlocks event) {
		
		int x = event.getX();
		int z = event.getZ();
		// y value (70) should be arbitrary.
		BlockPos pos = new BlockPos(x, 70, z);
		@SuppressWarnings("unchecked")
		T biome = (T) event.getWorld().getBiome(pos);
		
		if (biome instanceof BiomeRiver && Util.isBiomeNearby(pos, biome, event.getWorld())) {
			
			double[] depthBuffer = ((ChunkGeneratorOverworld) event.getGen()).depthBuffer;
			
	        depthBuffer = ((ChunkGeneratorOverworld) event.getGen()).surfaceNoise.getRegion(depthBuffer, (double)(x * 16), (double)(z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);

	        for (int i = 0; i < 16; ++i) {
	        	
	            for (int j = 0; j < 16; ++j) {
	            	biome.topBlock = BlockInit.WITCHS_GRASS.getDefaultState();
	            	biome.genTerrainBlocks(event.getWorld(), new Random(), event.getPrimer(), x * 16 + i, z * 16 + j, depthBuffer[j + i * 16]);
	            }
	        }
	        event.setResult(Result.DENY);
			//
			ChunkPrimer primer = event.getPrimer();
			
			int surfaceLevel = event.getWorld().getTopSolidOrLiquidBlock(pos).getY();
			
			try {
				surfaceLevel = primer.findGroundBlockIdx(x, z);
			}
			catch (IndexOutOfBoundsException e) {
				return;
			}
			
			// set grass block
			primer.setBlockState(event.getX(), surfaceLevel, event.getZ(), BlockInit.WITCHS_GRASS.getDefaultState());
			
			// set dirt blocks
			for (int i = surfaceLevel - 1; i >= surfaceLevel - 4; --i) {
				
				primer.setBlockState(event.getX(), surfaceLevel, event.getZ(), Blocks.DIRT.getDefaultState());
			}
			event.setResult(Result.DENY);
			//
		}
	}
}*/
