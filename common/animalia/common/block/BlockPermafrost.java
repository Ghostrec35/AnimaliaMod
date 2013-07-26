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
    
    @Override
    public int idDropped(int i, Random rand, int j)
    {
        float val = rand.nextFloat();
        if (val <= 0.1F) {
            return Animalia.mammothHair.itemID;
        } else if (val <= 0.3F) {
            return Animalia.mammothTrunkFrozen.itemID;
        } else {
            return Block.dirt.blockID;
        }
    }
}
