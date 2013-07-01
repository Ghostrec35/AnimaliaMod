package animalia.common.block;

import java.util.Random;

import cpw.mods.fml.common.FMLLog;

import animalia.common.Animalia;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockEarlyPaleozoicFossil extends Block implements IExtractable
{
	public final String blockName;
	
	public BlockEarlyPaleozoicFossil(int blockID, Material material, String blockName) 
	{
		super(blockID, material);
		this.blockName = blockName;
	}

	@Override
	public String getUnlocalizedName()
	{
		return "tile." + blockName;
	}

	@Override
	public ItemStack[] getExtractionPossiblities(int meta) 
	{
		return new ItemStack[]
		{
				
		};
	}
	
	@Override
	public int idDropped(int i, Random random, int j)
	{
		return 0;
	}
	
	//Returns the MetaData damage value of this Block to select the damage value of the Item
	@Override
	public int damageDropped(int blockDamage)
	{
		return blockDamage;
	}
	
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
	{
		
		world.setBlock(x, y, z, 0);
		if(world.isRemote)
			return true;
		
		if(player.getHeldItem().itemID == Animalia.olivinePickaxe.itemID)
			player.addChatMessage("Used Olivine Pickaxe");
		
		if(player.getHeldItem().getItem() instanceof ItemPickaxe && player.getHeldItem().itemID != Animalia.olivinePickaxe.itemID)
		{
			if(world.rand.nextInt(10) < 6)
			{
				EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Block.cobblestone));
				item.motionX = world.rand.nextDouble() - 0.5D;
				item.motionY = world.rand.nextDouble() - 0.5D;
				item.motionZ = world.rand.nextDouble() - 0.5D;
				world.spawnEntityInWorld(item);
			}
			else
			{
				EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Item.bone));
				item.motionX = world.rand.nextDouble() - 0.5D;
				item.motionY = world.rand.nextDouble() - 0.5D;
				item.motionZ = world.rand.nextDouble() - 0.5D;
				world.spawnEntityInWorld(item);
			}
		}
		else
		{
			EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Block.stone));
			item.motionX = 0;
			item.motionZ = 0;
			item.motionY += 0.001;
			world.spawnEntityInWorld(item);
		}
		return true;
	}
	
	private double getRandomItemMotion(Random rand)
	{
		if((rand.nextInt(2) + 1) % 2 == 0)
			return 0.001D;
		else
			return -0.001D;
	}
}
