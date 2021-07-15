package henryandalex.tinkersaddonmod.world.gen.structure.witchsvillage;

import java.util.Random;

import henryandalex.tinkersaddonmod.init.BlockInit;
import henryandalex.tinkersaddonmod.objects.blocks.BlockWitchsWoodLog;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
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
	public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBB) {
		if (averageGroundLvl < 0) {
			
            averageGroundLvl = getAverageGroundLevel(world, structureBB);

            if (averageGroundLvl < 0) {
            	
                return true;
            }

            boundingBox.offset(0, averageGroundLvl - boundingBox.maxY + 7 - 1, 0);
        }
		
		IBlockState air = Blocks.AIR.getDefaultState();
        
		// Witchs wood
        IBlockState logWitchs = BlockInit.WITCHS_WOOD_LOG.getDefaultState();
        IBlockState logWitchsSideways = logWitchs.withProperty(BlockWitchsWoodLog.LOG_AXIS, BlockLog.EnumAxis.X);
		IBlockState planksWitchs = BlockInit.WITCHS_WOOD_PLANKS.getDefaultState();
        
		// Acacia wood
		IBlockState logAcacia = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA);
        IBlockState planksAcacia = Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA);
        IBlockState stairsAcaciaNorth = Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
        IBlockState stairsAcaciaWestBottom = Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
        IBlockState stairsAcaciaWestTop = Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        IBlockState stairsAcaciaEastBottom = Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
        IBlockState stairsAcaciaEastTop = Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        IBlockState slabAcaciaBottom = Blocks.WOODEN_SLAB.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM);
        IBlockState slabAcaciaTop = Blocks.WOODEN_SLAB.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockSlab.HALF, EnumBlockHalf.TOP);
        
        //Stone Bricks
        IBlockState bricks = Blocks.STONEBRICK.getDefaultState();
        IBlockState stairsBrickWestBottom = Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
        IBlockState stairsBrickWestTop = Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        IBlockState stairsBrickEastBottom = Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
        IBlockState stairsBrickEastTop = Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        IBlockState slabBrickBottom = Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SMOOTHBRICK).withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        IBlockState slabBrickTop = Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SMOOTHBRICK).withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP);
         
        
        //miscellaneous
        IBlockState cobbleStoneWall = Blocks.COBBLESTONE_WALL.getDefaultState();
        IBlockState glassPanesGray = Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.GRAY);
        IBlockState ironBars = Blocks.IRON_BARS.getDefaultState();		
        
        /*
         * fillWithBlock -> (0,0,0) is bottom right/light (depending on the orientation of the bounding box) corner  
         * 					(length, height, depth) for front view again.
         */
        
        // plank base
        fillWithBlocks(world, structureBB, 1, 0, 1, 10, 4, 10, planksWitchs, air, false);
        
        // replace plank floor with stone
        fillWithBlocks(world, structureBB, 1, 0, 1, 10, 0, 10, bricks, bricks, false);
        
        // log posts in the corners on the outside
        fillWithBlocks(world, structureBB, 0, 0, 0, 0, 4, 0, logWitchs, logWitchs, false);
        fillWithBlocks(world, structureBB, 11, 0, 0, 11, 4, 0, logWitchs, logWitchs, false);
        
        // adding the door
        createVillageDoor(world, structureBB, random, 5, 1, 1, EnumFacing.NORTH);
        createVillageDoor(world, structureBB, random, 6, 1, 1, EnumFacing.NORTH);
        
        // place cross beam  
        fillWithBlocks(world, structureBB, 1, 4, 0, 10, 4, 0, logWitchsSideways, logWitchsSideways, false);
        
        // remove all grass blocks from doorway
        fillWithBlocks(world, structureBB, 1, 0, 0, 10, 3, 0, air, air, false);
        
        // add slab/block stone bricks on the bottom front
        fillWithBlocks(world, structureBB, 2, 0, 0, 3, 0, 0, slabBrickBottom, slabBrickBottom, false);
        fillWithBlocks(world, structureBB, 8, 0, 0, 9, 0, 0, slabBrickBottom, slabBrickBottom, false);
        setBlockState(world, bricks, 4, 0, 0, structureBB);
        setBlockState(world, bricks, 7, 0, 0, structureBB);
        
        // add stairs up to the door
        fillWithBlocks(world, structureBB, 5, 0, 0, 6, 0, 0, stairsAcaciaNorth, stairsAcaciaNorth, false);
        
        // fill in wood slabs/planks along the bottom of the beam
        fillWithBlocks(world, structureBB, 2, 3, 0, 9, 3, 0, slabAcaciaTop, slabAcaciaTop, false);
        setBlockState(world, planksAcacia, 4, 3, 0, structureBB);
        setBlockState(world, planksAcacia, 7, 3, 0, structureBB);
        
        // add iron bars next to door
        fillWithBlocks(world, structureBB, 4, 1, 0, 4, 2, 0, ironBars, ironBars, false);
        fillWithBlocks(world, structureBB, 7, 1, 0, 7, 2, 0, ironBars, ironBars, false);
        
        //set cobblestone pillars
        fillWithBlocks(world, structureBB, 1, 0, 0, 1, 3, 0, cobbleStoneWall, cobbleStoneWall, false);
        fillWithBlocks(world, structureBB, 10, 0, 0, 10, 3, 0, cobbleStoneWall, cobbleStoneWall, false);
        
        // add windows in the front 
        fillWithBlocks(world, structureBB, 2, 2, 1, 3, 2, 1, glassPanesGray, glassPanesGray, false);
        fillWithBlocks(world, structureBB, 8, 2, 1, 9, 2, 1, glassPanesGray, glassPanesGray, false);
        
        // add roof outline (Stone bricks)
        fillWithBlocks(world, structureBB, -1, 4, -1, -1, 4, 12, stairsBrickEastBottom, stairsBrickEastBottom, false);
        setBlockState(world, stairsBrickEastBottom, 0, 5, -1, structureBB);
        setBlockState(world, stairsBrickWestTop, 0, 4, -1, structureBB);
        setBlockState(world, stairsBrickEastBottom, 1, 6, -1, structureBB);
        setBlockState(world, stairsBrickWestTop, 1, 5, -1, structureBB);
        setBlockState(world, slabBrickBottom, 2, 7, -1, structureBB);
        setBlockState(world, stairsBrickWestTop, 2, 6, -1, structureBB);
        setBlockState(world, bricks, 3, 7, -1, structureBB);
        setBlockState(world, slabBrickTop, 4, 7, -1, structureBB);
        setBlockState(world, slabBrickBottom, 4, 8, -1, structureBB);
        setBlockState(world, bricks, 5, 8, -1, structureBB);
        //start of other side of roof outline (same as above but the length changes)
        setBlockState(world, bricks, 6, 8, -1, structureBB);
        setBlockState(world, slabBrickBottom, 7, 8, -1, structureBB);
        setBlockState(world, slabBrickTop, 7, 7, -1, structureBB);
        setBlockState(world, bricks, 8, 7, -1, structureBB);
        setBlockState(world, slabBrickBottom, 9, 7, -1, structureBB);
        setBlockState(world, stairsBrickEastTop, 9, 6, -1, structureBB);
        setBlockState(world, stairsBrickWestBottom, 10, 6, -1, structureBB);
        setBlockState(world, stairsBrickEastTop, 10, 5, -1, structureBB);
        setBlockState(world, stairsBrickWestBottom, 11, 5, -1, structureBB);
        setBlockState(world, stairsBrickEastTop, 11, 4, -1, structureBB);
        fillWithBlocks(world, structureBB, 12, 4, -1, 12, 4, 12, stairsBrickWestBottom, stairsBrickWestBottom, false);
        
        // now the roof outline in the back (same as segment before but the depth changes)
        setBlockState(world, stairsBrickEastBottom, 0, 5, 12, structureBB);
        setBlockState(world, stairsBrickWestTop, 0, 4, 12, structureBB);
        setBlockState(world, stairsBrickEastBottom, 1, 6, 12, structureBB);
        setBlockState(world, stairsBrickWestTop, 1, 5, 12, structureBB);
        setBlockState(world, slabBrickBottom, 2, 7, 12, structureBB);
        setBlockState(world, stairsBrickWestTop, 2, 6, 12, structureBB);
        setBlockState(world, bricks, 3, 7, 12, structureBB);
        setBlockState(world, slabBrickTop, 4, 7, 12, structureBB);
        setBlockState(world, slabBrickBottom, 4, 8, 12, structureBB);
        setBlockState(world, bricks, 5, 8, 12, structureBB);
        // start of other side of roof outline
        setBlockState(world, bricks, 6, 8, 12, structureBB);
        setBlockState(world, slabBrickBottom, 7, 8, 12, structureBB);
        setBlockState(world, slabBrickTop, 7, 7, 12, structureBB);
        setBlockState(world, bricks, 8, 7, 12, structureBB);
        setBlockState(world, slabBrickBottom, 9, 7, 12, structureBB);
        setBlockState(world, stairsBrickEastTop, 9, 6, 12, structureBB);
        setBlockState(world, stairsBrickWestBottom, 10, 6, 12, structureBB);
        setBlockState(world, stairsBrickEastTop, 10, 5, 12, structureBB);
        setBlockState(world, stairsBrickWestBottom, 11, 5, 12, structureBB);
        setBlockState(world, stairsBrickEastTop, 11, 4, 12, structureBB);
        
        // place wood roof (same as two segments before but the depth changes)
        fillWithBlocks(world, structureBB, 0, 5, 0, 0, 5, 11, stairsAcaciaEastBottom, stairsAcaciaEastBottom, false);
        fillWithBlocks(world, structureBB, 0, 4, 1, 0, 4, 10, stairsAcaciaWestTop, stairsAcaciaWestTop, false);
        fillWithBlocks(world, structureBB, 1, 6, 0, 1, 6, 11, stairsAcaciaEastBottom, stairsAcaciaEastBottom, false);
        fillWithBlocks(world, structureBB, 1, 5, 0, 1, 5, 11, stairsAcaciaWestTop, stairsAcaciaWestTop, false);
        fillWithBlocks(world, structureBB, 2, 7, 0, 2, 7, 11, slabAcaciaBottom, slabAcaciaBottom, false);
        fillWithBlocks(world, structureBB, 2, 6, 0, 2, 6, 11, stairsAcaciaWestTop, stairsAcaciaWestTop, false);
        fillWithBlocks(world, structureBB, 3, 7, 0, 3, 7, 11, planksAcacia, planksAcacia, false);
        fillWithBlocks(world, structureBB, 4, 7, 0, 4, 7, 11, slabAcaciaTop, slabAcaciaTop, false);
        fillWithBlocks(world, structureBB, 4, 8, 0, 4, 8, 11, slabAcaciaBottom, slabAcaciaBottom, false);
        fillWithBlocks(world, structureBB, 5, 8, 0, 5, 8, 11, slabAcaciaBottom, slabAcaciaBottom, false);
        // start of other side of roof outline
        fillWithBlocks(world, structureBB, 6, 8, 0, 6, 8, 11, slabAcaciaBottom, slabAcaciaBottom, false);
        fillWithBlocks(world, structureBB, 7, 8, 0, 7, 8, 11, slabAcaciaBottom, slabAcaciaBottom, false);
        fillWithBlocks(world, structureBB, 7, 7, 0, 7, 7, 11, slabAcaciaTop, slabAcaciaTop, false);
        fillWithBlocks(world, structureBB, 8, 7, 0, 8, 7, 11, planksAcacia, planksAcacia, false);
        fillWithBlocks(world, structureBB, 9, 7, 0, 9, 7, 11, slabAcaciaBottom, slabAcaciaBottom, false);
        fillWithBlocks(world, structureBB, 9, 6, 0, 9, 6, 11, stairsAcaciaEastTop, stairsAcaciaEastTop, false);
        fillWithBlocks(world, structureBB, 10, 6, 0, 10, 6, 11, stairsAcaciaWestBottom, stairsAcaciaWestBottom, false);
        fillWithBlocks(world, structureBB, 10, 5, 0, 10, 5, 11, stairsAcaciaEastTop, stairsAcaciaEastTop, false);
        fillWithBlocks(world, structureBB, 11, 5, 0, 11, 5, 11, stairsAcaciaWestBottom, stairsAcaciaWestBottom, false);
        fillWithBlocks(world, structureBB, 11, 4, 1, 11, 4, 10, stairsAcaciaEastTop, stairsAcaciaEastTop, false);
        
        // add Acacia log backdrop
        fillWithBlocks(world, structureBB, 1, 5, 1, 10, 5, 1, logAcacia, logAcacia, false);
        fillWithBlocks(world, structureBB, 2, 6, 1, 9, 6, 1, logAcacia, logAcacia, false);
        fillWithBlocks(world, structureBB, 4, 7, 1, 7, 7, 1, logAcacia, logAcacia, false);

        // place the blocks underneath the house to make it look supported. 
        for (int i1 = 0; i1 < 12; ++i1) {
        	
            for (int i2 = 0; i2 < 12; ++i2) {
            	
                clearCurrentPositionBlocksUpwards(world, i2, 9, i1, structureBB);
                replaceAirAndLiquidDownwards(world, bricks, i2, -1, i1, structureBB);
            }
        }
        
        spawnVillagers(world, structureBB, 4, 1, 2, 2);
        return true;
	}
	
	@Override
	protected BlockDoor biomeDoor() {
		return Blocks.ACACIA_DOOR;
	}
}
