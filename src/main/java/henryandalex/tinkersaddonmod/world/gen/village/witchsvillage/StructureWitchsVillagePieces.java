package henryandalex.tinkersaddonmod.world.gen.village.witchsvillage;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import henryandalex.tinkersaddonmod.blocks.BlockWitchsWoodLog;
import henryandalex.tinkersaddonmod.init.BlockInit;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.TemplateManager;

/*
 * Vanilla village Structure Key:
 * Church -> Church
 * House1 -> library
 * House2 -> Blacksmith
 * House3 -> Large House (L-shaped house)
 * House4Garden -> Small House (House with roof access)
 * Hall -> Butcher's Shop (House with backyard)
 * WoodHut -> Hut (House with dirt floors)
 * Field2 -> Small Farm
 * Field1 -> Large Farm
 */
public class StructureWitchsVillagePieces {
	
	public static List<StructureVillagePieces.PieceWeight> getStructureVillageWeightedPieceList(Random random, int size) {
		List<StructureVillagePieces.PieceWeight> list = Lists.<StructureVillagePieces.PieceWeight>newArrayList();
		list.add(new StructureVillagePieces.PieceWeight(StructureWitchsVillagePieces.House.class, 20, 8/*MathHelper.getInt(random, 8 + size, 14 + size)*/));
		Iterator<StructureVillagePieces.PieceWeight> iterator = list.iterator();
		
        while (iterator.hasNext()) {
            if ((iterator.next()).villagePiecesLimit == 0) {
                iterator.remove();
            }
        }
        
        return list;
	}
	
	/**
	 * Will return -1 if it is not in the list
	 * 
	 * @param list List to check
	 * @param structurecomponent Component to check for
	 * @return Index of the piece in the given list
	 */
	public static int getPieceIndexFromList(List<StructureVillagePieces.PieceWeight> list, StructureComponent structurecomponent) {
		int i = 0;
        for (StructureVillagePieces.PieceWeight piece : list) {
        	if (piece.villagePieceClass.equals(structurecomponent.getClass())) {
        		return i;
        	}
        	else {
        		i++;
    		}
        }
        return -1;
	}
    
	@SuppressWarnings("unused")
	private static int updatePieceWeight(List<StructureVillagePieces.PieceWeight> p_75079_0_) {
		
        boolean flag = false;
        int i = 0;

        for (StructureVillagePieces.PieceWeight structurevillagepieces$pieceweight : p_75079_0_) {
            if (structurevillagepieces$pieceweight.villagePiecesLimit > 0 && structurevillagepieces$pieceweight.villagePiecesSpawned < structurevillagepieces$pieceweight.villagePiecesLimit) {
                flag = true;
            }

            i += structurevillagepieces$pieceweight.villagePieceWeight;
        }

        return flag ? i : -1;
    }
	
	/*
    private static StructureComponent generateAndAddComponent(StructureVillagePieces.Start start, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType) {
        
    	if (componentType > 50) {
            return null;
        }
        else if (Math.abs(structureMinX - start.getBoundingBox().minX) <= 112 && Math.abs(structureMinZ - start.getBoundingBox().minZ) <= 112) {
            StructureComponent structurecomponent = generateComponent(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType + 1);

            if (structurecomponent != null) {
                structureComponents.add(structurecomponent);
                start.pendingHouses.add(structurecomponent);
                return structurecomponent;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }
    
    private static StructureVillagePieces.Village generateComponent(StructureVillagePieces.Start start, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType) {
       
    	int i = updatePieceWeight(start.structureVillageWeightedPieceList);

        if (i <= 0) {
            return null;
        }
        else {
            int j = 0;

            while (j < 5) {
                ++j;
                int k = rand.nextInt(i);

                for (StructureVillagePieces.PieceWeight structurevillagepieces$pieceweight : start.structureVillageWeightedPieceList) {
                    k -= structurevillagepieces$pieceweight.villagePieceWeight;

                    if (k < 0) {
                        if (!structurevillagepieces$pieceweight.canSpawnMoreVillagePiecesOfType(componentType) || structurevillagepieces$pieceweight == start.lastPlaced && start.structureVillageWeightedPieceList.size() > 1) {
                            break;
                        }

                        StructureVillagePieces.Village structurevillagepieces$village = findAndCreateComponentFactory(start, structurevillagepieces$pieceweight, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);

                        if (structurevillagepieces$village != null) {
                            ++structurevillagepieces$pieceweight.villagePiecesSpawned;
                            start.lastPlaced = structurevillagepieces$pieceweight;

                            if (!structurevillagepieces$pieceweight.canSpawnMoreVillagePieces()) {
                                start.structureVillageWeightedPieceList.remove(structurevillagepieces$pieceweight);
                            }

                            return structurevillagepieces$village;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = StructureVillagePieces.Torch.findPieceBox(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing);

            if (structureboundingbox != null) {
                return new StructureVillagePieces.Torch(start, componentType, rand, structureboundingbox, facing);
            }
            else {
                return null;
            }
        }
    }
    */

	private static StructureComponent generateAndAddRoadPiece(StructureVillagePieces.Start start, List<StructureComponent> p_176069_1_, Random rand, int p_176069_3_, int p_176069_4_, int p_176069_5_, EnumFacing facing, int p_176069_7_) {
        
    	if (p_176069_7_ > 3 + start.terrainType) {
            return null;
        }
        else if (Math.abs(p_176069_3_ - start.getBoundingBox().minX) <= 112 && Math.abs(p_176069_5_ - start.getBoundingBox().minZ) <= 112) {
            StructureBoundingBox structureboundingbox = StructureWitchsVillagePieces.CustomPath.findPieceBox(start, p_176069_1_, rand, p_176069_3_, p_176069_4_, p_176069_5_, facing);

            if (structureboundingbox != null && structureboundingbox.minY > 10) {
                StructureComponent structurecomponent = new StructureWitchsVillagePieces.CustomPath(start, p_176069_7_, rand, structureboundingbox, facing);
                p_176069_1_.add(structurecomponent);
                start.pendingRoads.add(structurecomponent);
                return structurecomponent;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }
    /*
    private static StructureVillagePieces.Village findAndCreateComponentFactory(StructureVillagePieces.Start start, StructureVillagePieces.PieceWeight weight, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType) {
        
    	Class <? extends StructureVillagePieces.Village > oclass = weight.villagePieceClass;
        StructureVillagePieces.Village structurevillagepieces$village = null;

        if (oclass == StructureWitchsVillagePieces.House.class) {
            structurevillagepieces$village = StructureWitchsVillagePieces.House.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else {
            structurevillagepieces$village = net.minecraftforge.fml.common.registry.VillagerRegistry.getVillageComponent(weight, start , structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }

        return structurevillagepieces$village;
    }
    
    public abstract static class Village extends StructureVillagePieces.Village {
    	
    	public Village() {}
    	
    	public Village(StructureVillagePieces.Start parStart, int parType) {
    		super(parStart, parType);
    	}
    	
    	@Override
    	@Nullable
        protected StructureComponent getNextComponentNN(StructureVillagePieces.Start start, List<StructureComponent> structureComponents, Random rand, int p_74891_4_, int p_74891_5_)
        {
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != null)
            {
                switch (enumfacing)
                {
                    case NORTH:
                    default:
                        return StructureWitchsVillagePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                    case SOUTH:
                        return StructureWitchsVillagePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                    case WEST:
                        return StructureWitchsVillagePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    case EAST:
                        return StructureWitchsVillagePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }
            else
            {
                return null;
            }
        }

    	@Override
        @Nullable
        protected StructureComponent getNextComponentPP(StructureVillagePieces.Start start, List<StructureComponent> structureComponents, Random rand, int p_74894_4_, int p_74894_5_)
        {
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != null)
            {
                switch (enumfacing)
                {
                    case NORTH:
                    default:
                        return StructureWitchsVillagePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                    case SOUTH:
                        return StructureWitchsVillagePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                    case WEST:
                        return StructureWitchsVillagePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    case EAST:
                        return StructureWitchsVillagePieces.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            }
            else
            {
                return null;
            }
        }
    }
    */
    
	public static class House extends StructureVillagePieces.Village {

		public House() {}

		public House(StructureVillagePieces.Start parStart, int parType, Random parRand, StructureBoundingBox parStructBB, EnumFacing parFacing) {
			super(parStart, parType);
			setCoordBaseMode(parFacing);
			this.boundingBox = parStructBB;
		}
		
	    public static StructureVillagePieces.Village createPiece(StructureVillagePieces.Start start, List<StructureComponent> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_) {
	        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 14, 10, 14, facing);
	        return House.findIntersecting(p_175858_1_, structureboundingbox) == null ? new StructureWitchsVillagePieces.House(start, p_175858_7_, rand, structureboundingbox, facing) : null;
	    }
	    
	    /*
	    public static StructureComponent findIntersecting(List<StructureComponent> listIn, StructureBoundingBox box1) {
	    	for (StructureComponent comp : listIn) {
	    		StructureBoundingBox box2 = comp.getBoundingBox();
	    		
	    		for (int x = box2.minX; x == box2.minX; x = box2.maxX) {
	    			for (int y = box2.minY; y == box2.minY; y = box2.maxY) {
	    				for (int z = box2.minZ; z == box2.minZ; z = box2.maxZ) {
	    					if (Util.boxContains(new BlockPos(x, y, z), box1)) {
	    						return comp;
	    					}
	    				}
	    			}
	    		}
	    	}
	    	return null;
	    }
	    */
	    
		@Override
		public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBB) {
			
			if (averageGroundLvl < 0) {
				
	            averageGroundLvl = getAverageGroundLevel(world, structureBB);

	            if (averageGroundLvl < 0) {
	            	
	                return true;
	            }

	            boundingBox.offset(0, averageGroundLvl - boundingBox.maxY + 10 - 1, 0);
	        }
			
			IBlockState air = Blocks.AIR.getDefaultState();
	        
			// Witchs wood
	        IBlockState logWitchs = BlockInit.WITCHS_WOOD_LOG.getDefaultState();
	        IBlockState logWitchsSideways = logWitchs.withProperty(BlockWitchsWoodLog.LOG_AXIS, BlockLog.EnumAxis.X);
			IBlockState planksWitchs = BlockInit.WITCHS_WOOD_PLANKS.getDefaultState();
			//IBlockState stairsWitchsNorth = BlockInit.WITCHS_WOOD_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
			IBlockState stairsWitchsWestBottom = BlockInit.WITCHS_WOOD_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
	        IBlockState stairsWitchsWestTop = BlockInit.WITCHS_WOOD_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
	        IBlockState stairsWitchsEastBottom = BlockInit.WITCHS_WOOD_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
	        IBlockState stairsWitchsEastTop = BlockInit.WITCHS_WOOD_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
	        IBlockState slabWitchsBottom = BlockInit.WITCHS_WOOD_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM);
	        IBlockState slabWitchsTop = BlockInit.WITCHS_WOOD_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.TOP);
	        
			// Acacia wood
			IBlockState logAcacia = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA);
			IBlockState planksAcacia = Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA);
			IBlockState stairsAcaciaNorth = Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
	        /*
			IBlockState stairsAcaciaWestBottom = Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
	        IBlockState stairsAcaciaWestTop = Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
	        IBlockState stairsAcaciaEastBottom = Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
	        IBlockState stairsAcaciaEastTop = Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
	        IBlockState slabAcaciaBottom = Blocks.WOODEN_SLAB.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM);
	        */
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
	        // TODO: make staircase instead
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
	        fillWithBlocks(world, structureBB, 0, 5, 0, 0, 5, 11, stairsWitchsEastBottom, stairsWitchsEastBottom, false);
	        fillWithBlocks(world, structureBB, 0, 4, 1, 0, 4, 10, stairsWitchsWestTop, stairsWitchsWestTop, false);
	        fillWithBlocks(world, structureBB, 1, 6, 0, 1, 6, 11, stairsWitchsEastBottom, stairsWitchsEastBottom, false);
	        fillWithBlocks(world, structureBB, 1, 5, 0, 1, 5, 11, stairsWitchsWestTop, stairsWitchsWestTop, false);
	        fillWithBlocks(world, structureBB, 2, 7, 0, 2, 7, 11, slabWitchsBottom, slabWitchsBottom, false);
	        fillWithBlocks(world, structureBB, 2, 6, 0, 2, 6, 11, stairsWitchsWestTop, stairsWitchsWestTop, false);
	        fillWithBlocks(world, structureBB, 3, 7, 0, 3, 7, 11, planksWitchs, planksWitchs, false);
	        fillWithBlocks(world, structureBB, 4, 7, 0, 4, 7, 11, slabWitchsTop, slabWitchsTop, false);
	        fillWithBlocks(world, structureBB, 4, 8, 0, 4, 8, 11, slabWitchsBottom, slabWitchsBottom, false);
	        fillWithBlocks(world, structureBB, 5, 8, 0, 5, 8, 11, slabWitchsBottom, slabWitchsBottom, false);
	        // start of other side of roof outline
	        fillWithBlocks(world, structureBB, 6, 8, 0, 6, 8, 11, slabWitchsBottom, slabWitchsBottom, false);
	        fillWithBlocks(world, structureBB, 7, 8, 0, 7, 8, 11, slabWitchsBottom, slabWitchsBottom, false);
	        fillWithBlocks(world, structureBB, 7, 7, 0, 7, 7, 11, slabWitchsTop, slabWitchsTop, false);
	        fillWithBlocks(world, structureBB, 8, 7, 0, 8, 7, 11, planksWitchs, planksWitchs, false);
	        fillWithBlocks(world, structureBB, 9, 7, 0, 9, 7, 11, slabWitchsBottom, slabWitchsBottom, false);
	        fillWithBlocks(world, structureBB, 9, 6, 0, 9, 6, 11, stairsWitchsEastTop, stairsWitchsEastTop, false);
	        fillWithBlocks(world, structureBB, 10, 6, 0, 10, 6, 11, stairsWitchsWestBottom, stairsWitchsWestBottom, false);
	        fillWithBlocks(world, structureBB, 10, 5, 0, 10, 5, 11, stairsWitchsEastTop, stairsWitchsEastTop, false);
	        fillWithBlocks(world, structureBB, 11, 5, 0, 11, 5, 11, stairsWitchsWestBottom, stairsWitchsWestBottom, false);
	        fillWithBlocks(world, structureBB, 11, 4, 1, 11, 4, 10, stairsWitchsEastTop, stairsWitchsEastTop, false);
	        
	        // add Acacia log backdrop
	        fillWithBlocks(world, structureBB, 1, 5, 1, 10, 5, 1, logAcacia, logAcacia, false);
	        fillWithBlocks(world, structureBB, 2, 6, 1, 9, 6, 1, logAcacia, logAcacia, false);
	        fillWithBlocks(world, structureBB, 4, 7, 1, 7, 7, 1, logAcacia, logAcacia, false);
	        // back side Acacia logs
	        fillWithBlocks(world, structureBB, 1, 5, 10, 10, 5, 10, logAcacia, logAcacia, false);
	        fillWithBlocks(world, structureBB, 2, 6, 10, 9, 6, 10, logAcacia, logAcacia, false);
	        fillWithBlocks(world, structureBB, 4, 7, 10, 7, 7, 10, logAcacia, logAcacia, false);
	        
	        // features on the back wall. 

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
	
	public static class CustomPath extends StructureVillagePieces.Path {
		
		private int length;
		
		public CustomPath(StructureVillagePieces.Start start, int p_176069_7_, Random rand, StructureBoundingBox structureboundingbox, EnumFacing facing) {
			super(start, p_176069_7_, rand, structureboundingbox, facing);
            this.length = Math.max(structureboundingbox.getXSize(), structureboundingbox.getZSize());
		}
		
		@Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("Length", this.length);
        }

		@Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
        {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
            this.length = tagCompound.getInteger("Length");
        }
		
		@Override
		public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
			
            boolean flag = false;

            for (int i = rand.nextInt(5); i < this.length - 8; i += 2 + rand.nextInt(5)) {
            	
                StructureComponent structurecomponent = this.getNextComponentNN((StructureVillagePieces.Start)componentIn, listIn, rand, 0, i);

                if (structurecomponent != null) {
                	
                    i += Math.max(structurecomponent.getBoundingBox().getXSize(), structurecomponent.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            for (int j = rand.nextInt(5); j < this.length - 8; j += 2 + rand.nextInt(5))
            {
                StructureComponent structurecomponent1 = this.getNextComponentPP((StructureVillagePieces.Start)componentIn, listIn, rand, 0, j);

                if (structurecomponent1 != null)
                {
                    j += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            EnumFacing enumfacing = this.getCoordBaseMode();

            if (flag && rand.nextInt(3) > 0 && enumfacing != null)
            {
                switch (enumfacing)
                {
                    case NORTH:
                    default:
                    	StructureWitchsVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, this.getComponentType());
                        break;
                    case SOUTH:
                    	StructureWitchsVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.WEST, this.getComponentType());
                        break;
                    case WEST:
                    	StructureWitchsVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                        break;
                    case EAST:
                    	StructureWitchsVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }

            if (flag && rand.nextInt(3) > 0 && enumfacing != null)
            {
                switch (enumfacing)
                {
                    case NORTH:
                    default:
                    	StructureWitchsVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, this.getComponentType());
                        break;
                    case SOUTH:
                    	StructureWitchsVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.EAST, this.getComponentType());
                        break;
                    case WEST:
                    	StructureWitchsVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                        break;
                    case EAST:
                    	StructureWitchsVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            }
        }
		
		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
			
            IBlockState iblockstate = Blocks.STONEBRICK.getDefaultState();
            IBlockState iblockstate1 = BlockInit.WITCHS_WOOD_PLANKS.getDefaultState();
            IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.GRAVEL.getDefaultState());
            IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());

            for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i) {
            	
                for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j) {
                	
                    BlockPos blockpos = new BlockPos(i, 64, j);

                    if (structureBoundingBoxIn.isVecInside(blockpos)) {
                    	
                        blockpos = worldIn.getTopSolidOrLiquidBlock(blockpos).down();

                        if (blockpos.getY() < worldIn.getSeaLevel()) {
                        	
                            blockpos = new BlockPos(blockpos.getX(), worldIn.getSeaLevel() - 1, blockpos.getZ());
                        }

                        while (blockpos.getY() >= worldIn.getSeaLevel() - 1) {
                        	
                            IBlockState iblockstate4 = worldIn.getBlockState(blockpos);

                            if (iblockstate4.getBlock() == BlockInit.WITCHS_GRASS && worldIn.isAirBlock(blockpos.up())) {
                            	
                                worldIn.setBlockState(blockpos, iblockstate, 2);
                                break;
                            }

                            if (iblockstate4.getMaterial().isLiquid()) {
                            	
                                worldIn.setBlockState(blockpos, iblockstate1, 2);
                                break;
                            }

                            if (iblockstate4.getBlock() == Blocks.SAND || iblockstate4.getBlock() == Blocks.SANDSTONE || iblockstate4.getBlock() == Blocks.RED_SANDSTONE) {
                            	
                                worldIn.setBlockState(blockpos, iblockstate2, 2);
                                worldIn.setBlockState(blockpos.down(), iblockstate3, 2);
                                break;
                            }

                            blockpos = blockpos.down();
                        }
                    }
                }
            }
            return true;
        }
	}
	
	public static class Start extends StructureVillagePieces.Start {
        public Start(BiomeProvider biomeProviderIn, int i, Random rand, int p_i2104_4_, int p_i2104_5_, List<StructureVillagePieces.PieceWeight> p_i2104_6_, int p_i2104_7_) {
            super(biomeProviderIn, i, rand, p_i2104_4_, p_i2104_5_, p_i2104_6_, p_i2104_7_);
            this.isZombieInfested = false;
        } 
        
        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
        	
        	StructureWitchsVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
        	StructureWitchsVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
        	StructureWitchsVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
        	StructureWitchsVillagePieces.generateAndAddRoadPiece((StructureVillagePieces.Start)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
        }
    }
}
