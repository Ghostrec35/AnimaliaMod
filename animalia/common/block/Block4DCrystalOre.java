package animalia.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import animalia.common.Animalia;

public class Block4DCrystalOre extends Block
{
	private boolean isGlowing;
	
	public Block4DCrystalOre(int blockID)
	{
		super(blockID, Material.rock);
	}
	
	public int idDropped(int i, Random random, int j)
	{
		return Animalia.crystal4D.itemID;
	}
	
    public void onBlockClicked(World world, int xCoord, int yCoord, int zCoord, EntityPlayer entityPlayer)
    {
        this.startGlowing(world, xCoord, yCoord, zCoord);
        super.onBlockClicked(world, xCoord, yCoord, zCoord, entityPlayer);
    }
    
    private void startGlowing(World world, int xCoord, int yCoord, int zCoord)
    {
        if (this.blockID == Animalia.crystal4DOre.blockID)
        {
            world.setBlock(xCoord, yCoord, zCoord, Animalia.crystal4DOreGlowing.blockID);
        }
    }
}
