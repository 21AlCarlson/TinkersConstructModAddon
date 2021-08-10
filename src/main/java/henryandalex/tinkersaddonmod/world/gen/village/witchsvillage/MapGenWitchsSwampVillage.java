package henryandalex.tinkersaddonmod.world.gen.village.witchsvillage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import henryandalex.tinkersaddonmod.init.BiomeInit;
import henryandalex.tinkersaddonmod.world.gen.village.MapGenCustomVillageBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class MapGenWitchsSwampVillage extends MapGenCustomVillageBase {
	
	@Override
	public String getStructureName() {
		return "Witchs Wood Village";
	}
	
	/*
	 * Needs to be public to allow access to it from MapGenVillageExtension.
	 * Also, NEED to initialize villageSpawnBiomes in the constructor
	 */
    public MapGenWitchsSwampVillage() {
    	super();
        villageSpawnBiomes = Arrays.<Biome>asList(BiomeInit.WITCHS_SWAMP);
	}
    
    /*
    public MapGenWitchsSwampVillage(Map<String, String> map)
    {
        this();

        for (Entry<String, String> entry : map.entrySet())
        {
            if (((String)entry.getKey()).equals("size"))
            {
                this.size = MathHelper.getInt(entry.getValue(), this.size, 0);
            }
            else if (((String)entry.getKey()).equals("distance"))
            {
                this.distance = MathHelper.getInt(entry.getValue(), this.distance, 9);
            }
        }
    }
	*/

	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		this.world = worldIn;
        return findNearestStructurePosBySpacing(worldIn, this, pos, this.distance, 8, 10387312, false, 100, findUnexplored);
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.distance - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= this.distance - 1;
        }

        int k = chunkX / this.distance;
        int l = chunkZ / this.distance;
        Random random = this.world.setRandomSeed(k, l, 10387312);
        k = k * this.distance;
        l = l * this.distance;
        k = k + random.nextInt(this.distance - 8);
        l = l + random.nextInt(this.distance - 8);

        if (i == k && j == l)
        {
            boolean flag = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, MapGenWitchsSwampVillage.villageSpawnBiomes);

            if (flag)
            {
                return true;
            }
        }

        return false;
	}
	
	/*
	 * This is needed because we need a way to get the village spawn biomes from an instance 
	 * (non-static) in the method MapGenVillageHandler#registerVillage(T villageGenerator).
	 */
	@Override
	public List<Biome> getVillageSpawnBiomes() {
		return MapGenWitchsSwampVillage.villageSpawnBiomes;
	}

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenWitchsSwampVillage.Start(world, rand, chunkX, chunkZ, size);
	}
	
	public class Start extends StructureStart
	{
        private boolean hasMoreThanTwoComponents;

        public Start()
        {
        }

        public Start(World worldIn, Random rand, int x, int z, int size)
        {
            super(x, z);
            List<StructureVillagePieces.PieceWeight> list = StructureWitchsVillagePieces.getStructureVillageWeightedPieceList(rand, size);
            StructureWitchsVillagePieces.Start structurevillagepieces$start = new StructureWitchsVillagePieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
            // adds the well which is the starting point for the town.
            this.components.add(structurevillagepieces$start);
            // generates the road around the well.
            structurevillagepieces$start.buildComponent(structurevillagepieces$start, this.components, rand);
            List<StructureComponent> roads = structurevillagepieces$start.pendingRoads;
            List<StructureComponent> houses = structurevillagepieces$start.pendingHouses;
            // will cycle through until both lists are empty
            while (!roads.isEmpty() || !houses.isEmpty())
            {
                if (roads.isEmpty())
                {
                    int i = rand.nextInt(houses.size());
                    StructureComponent structurecomponent = houses.remove(i);
                    // adds some buildings
                    // typically is just the generic method found in StrucutreComponent.
                    structurecomponent.buildComponent(structurevillagepieces$start, this.components, rand);
                }
                else
                {
                    int j = rand.nextInt(roads.size());
                    StructureComponent structurecomponent2 = roads.remove(j);
                    // add more paths and buildings.
                    structurecomponent2.buildComponent(structurevillagepieces$start, this.components, rand);
                }
            }

            this.updateBoundingBox();
            int k = 0;

            for (StructureComponent structurecomponent1 : this.components)
            {
                if (!(structurecomponent1 instanceof StructureVillagePieces.Road))
                {
                    ++k;
                }
            }

            this.hasMoreThanTwoComponents = k > 2;
        }

        public boolean isSizeableStructure()
        {
            return this.hasMoreThanTwoComponents;
        }

        public void writeToNBT(NBTTagCompound tagCompound)
        {
            super.writeToNBT(tagCompound);
            tagCompound.setBoolean("Valid", this.hasMoreThanTwoComponents);
        }

        public void readFromNBT(NBTTagCompound tagCompound)
        {
            super.readFromNBT(tagCompound);
            this.hasMoreThanTwoComponents = tagCompound.getBoolean("Valid");
        }
    }
}
