package henryandalex.tinkersaddonmod.world.gen.witchsvillage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import henryandalex.tinkersaddonmod.TCAddonMod;
import henryandalex.tinkersaddonmod.init.BiomeInit;
import henryandalex.tinkersaddonmod.utils.Util;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class MapGenWitchsSwampVillage extends MapGenVillage {

	public static List<Biome> VILLAGE_SPAWN_BIOMES = Arrays.<Biome>asList(BiomeInit.WITCHS_SWAMP);
    private int size;
    private int distance;
    @SuppressWarnings("unused")
	private final int minTownSeparation;
	
	@Override
	public String getStructureName() {
		return "Witchs Wood Village";
	}
	
    public MapGenWitchsSwampVillage() {
        this.distance = 32;
        this.minTownSeparation = 8;
	}
    
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
            boolean flag = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, MapGenWitchsSwampVillage.VILLAGE_SPAWN_BIOMES);

            if (flag)
            {
                return true;
            }
        }

        return false;
	}
	
	public boolean canSpawnStructureAtCoordsPublic(World world, int chunkX, int chunkZ) {
		this.world = world;
		return this.canSpawnStructureAtCoords(chunkX, chunkZ);
	}

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenWitchsSwampVillage.Start(world, rand, chunkX, chunkZ, size);
	}
	
	public StructureStart getStructureStartPublic(int chunkX, int chunkZ) {
		StructureStart val = this.getStructureStart(chunkX, chunkZ);
		/* 
		 * Since this is accessed outside of the class, there is no way to put the new instance 
		 * of the StartStructure into the structureMap which stores all the Villages. This is 
		 * usually done right after calling this method in MapGenStructure#recursiveGenerate() 
		 * Therefore, you need to add it here before you return the new StrucutreStart.
		 */
		this.structureMap.put(ChunkPos.asLong(chunkX, chunkZ), val);
		return val;
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
            //TODO: remove coord printing in final release
            TCAddonMod.instance.getLogger().info("(Chunk x: " + x + ", Chunk z: " + z + ")");
            BlockPos pos = Util.getPosFromChunkCoords(x, z);
            TCAddonMod.instance.getLogger().info("(x: " + pos.getX() + ", z: " + pos.getZ() + ")");
            List<StructureVillagePieces.PieceWeight> list = StructureWitchsVillagePieces.getStructureVillageWeightedPieceList(rand, size);
            StructureWitchsVillagePieces.Start structurevillagepieces$start = new StructureWitchsVillagePieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
            this.components.add(structurevillagepieces$start);
            structurevillagepieces$start.buildComponent(structurevillagepieces$start, this.components, rand);
            List<StructureComponent> list1 = structurevillagepieces$start.pendingRoads;
            List<StructureComponent> list2 = structurevillagepieces$start.pendingHouses;

            while (!list1.isEmpty() || !list2.isEmpty())
            {
                if (list1.isEmpty())
                {
                    int i = rand.nextInt(list2.size());
                    StructureComponent structurecomponent = list2.remove(i);
                    structurecomponent.buildComponent(structurevillagepieces$start, this.components, rand);
                }
                else
                {
                    int j = rand.nextInt(list1.size());
                    StructureComponent structurecomponent2 = list1.remove(j);
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
