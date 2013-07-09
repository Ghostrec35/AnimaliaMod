package animalia.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import animalia.common.Animalia;

public class BlockPermafrost extends Block
{
    public BlockPermafrost(int par1)
    {
        super(par1, Material.grass);
    }
    
    public int idDropped(int i, Random rand, int j)
    {
        int val = rand.nextInt(10);
        if(val == 0)
            return Animalia.mammothHair.itemID;
        else if(val == 1 || val == 2)
            return Animalia.mammothTrunkFrozen.itemID;
        else
            return Block.dirt.blockID;
    }
}
