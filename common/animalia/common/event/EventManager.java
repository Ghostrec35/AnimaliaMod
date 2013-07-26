package animalia.common.event;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import animalia.common.Animalia;
import animalia.common.Text.TextColor;
import animalia.common.ref.Reference;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.IPickupNotifier;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.IWorldGenerator;

public class EventManager implements ICraftingHandler, IFuelHandler, IWorldGenerator, IPlayerTracker, IPickupNotifier
{
	@ForgeSubscribe
	public void onLivingEntityHurt(LivingDeathEvent event)
	{

	}
	
	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player)
	{
	    
	}

	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{
            switch(Animalia.isCurrentVersion)
            {
                case Animalia.TRUE:
                    player.addChatMessage(TextColor.GOLD.getColorString() + "You have the most current version of the Animalia mod installed");
                    break;
                case Animalia.FALSE:
                    player.addChatMessage(TextColor.RED.getColorString() + "You do not have the most recent recommended build of Animalia installed. The most recent version is: " + Animalia.latestModVersion + "; however, your current version is: " + Reference.MOD_VERSION);
                    break;
                case Animalia.ERROR:
                    player.addChatMessage(TextColor.DARKRED.getColorString() /*Currently issues with using Italics+ animalia.common.Text.TextFormat.ITALICS*/ + "Animalia mod was unable to check for updates.");
                    break;
                case Animalia.UNCHECKED:
                    //Never checked
                    break;
                default:
                    //wut..
                    break;
            }
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) 
	{
		
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) 
	{
		
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) 
	{
		
	}

	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		return 0;
	}

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) 
	{
		
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) 
	{
		
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		switch(world.provider.dimensionId)
		{
    		case -1: generateNether(world, random, chunkX * 16, chunkZ * 16);
    		case 0: generateSurface(world, random, chunkX * 16, chunkZ * 16);
    		case 1: generateEnd(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateNether(World world, Random random, int blockX, int blockZ) 
	{
		
	}

	private void generateSurface(World world, Random random, int blockX, int blockZ) 
	{
		//Full Documentation for this Helper methods found below.
		this.addOreSpawn(Animalia.fossilBlock, world, random, blockX, blockZ, 16, 16, 40, 10, 0, 20, 0);
		this.addOreSpawn(Animalia.fossilBlock, world, random, blockX, blockZ, 16, 16, 40, 10, 20, 40, 1);
		this.addOreSpawn(Animalia.fossilBlock, world, random, blockX, blockZ, 16, 16, 40, 10, 40, 60, 2);
		
		this.addOreSpawn(Animalia.crystal4DOre, world, random, blockX, blockZ, 16, 16, 30, 30, 6);
	}

	private void generateEnd(World world, Random random, int blockX, int blockZ) 
	{
		
	}

	/**
	 * Adds an Ore Spawn to Minecraft. Simply register all Ores to spawn with this method in your Generation method in your IWorldGeneration extending Class
	 * 
	 * @param The Block to spawn
	 * @param The World to spawn in
	 * @param A Random object for retrieving random positions within the world to spawn the Block
	 * @param An int for passing the X-Coordinate for the Generation method
	 * @param An int for passing the Z-Coordinate for the Generation method
	 * @param An int for setting the maximum X-Coordinate values for spawning on the X-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum Z-Coordinate values for spawning on the Z-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum size of a vein
	 * @param An int for the Number of chances available for the Block to spawn per-chunk
	 * @param An int for the maximum Y-Coordinate height at which this block may spawn
	 **/
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int maxY)
	{
		addOreSpawn(block, world, random, blockXPos, blockZPos, maxX, maxZ, maxVeinSize, chancesToSpawn, 0, maxY, 0);
	}

	/**
	 * Adds an Ore Spawn to Minecraft. Simply register all Ores to spawn with this method in your Generation method in your IWorldGeneration extending Class
	 * 
	 * @param The Block to spawn
	 * @param The World to spawn in
	 * @param A Random object for retrieving random positions within the world to spawn the Block
	 * @param An int for passing the X-Coordinate for the Generation method
	 * @param An int for passing the Z-Coordinate for the Generation method
	 * @param An int for setting the maximum X-Coordinate values for spawning on the X-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum Z-Coordinate values for spawning on the Z-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum size of a vein
	 * @param An int for the Number of chances available for the Block to spawn per-chunk
	 * @param An int for the minimum Y-Coordinate height at which this block may spawn
	 * @param An int for the maximum Y-Coordinate height at which this block may spawn
	 * @param An int for the metadata of the Block
	 **/
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY, int metadata) 
	{
		assert maxY > minY: "The maximum Y must be greater than the Minimum Y";
		assert maxX > 0 && maxX <= 16: "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
		assert minY > 0: "addOreSpawn: The Minimum Y must be greater than 0";
		assert maxY < 256 && maxY > 0: "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
		assert maxZ > 0 && maxZ <= 16: "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";
		//assert metadata > 0 && metadata <= 16: "addOreSpawn: The metadata value used is invalid! Value: " + metadata; (194, 34, 338)
		
		int diffBtwnMinMaxY = maxY - minY;
		for(int x = 0; x < chancesToSpawn; x++)
		{
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZPos + random.nextInt(maxZ);
			
			if(Reference.DEBUG_MODE)
			{
			    FMLLog.info("Fossil Blocks Spawned Here: (" + posX + ", " + posY + ", " + posZ + ")");
			}
			
			(new WorldGenMinable(block.blockID, metadata, maxVeinSize, Block.stone.blockID)).generate(world, random, posX, posY, posZ);
		}
	}
}
