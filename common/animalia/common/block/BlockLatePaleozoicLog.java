package animalia.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockLatePaleozoicLog extends BlockLog
{
    /** The type of tree this log came from. */
	private String[] treeNames = 
		{
			"sigillaria",
			"lepidodendron",
			"cordaites",
			"tree_4",
			"tree_5",
		};
    
    @SideOnly(Side.CLIENT)
    private Icon[] tree_side;
    @SideOnly(Side.CLIENT)
    private Icon[] tree_top;

    public BlockLatePaleozoicLog(int id)
    {
        super(id);
        tree_side = new Icon[5];
        tree_top = new Icon[5];
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 - par1%3;
    }

    /**
     * returns a number between 0 and 3
     */
    public static int limitToValidMetadata(int par0)
    {
        return par0 & 3;
    }
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        int k = par2 / 3;
        int l = par2 % 3;
        
    	if(l == 0 && (par1 == 1 || par1 == 0)){
    		return this.tree_top[k];
    	}
    	if(l == 1 && (par1 == 2 || par1 == 3)){
    		return this.tree_top[k];
    	}
    	if(l == 2 && (par1 == 4 || par1 == 5)){
    		return this.tree_top[k];
    	}
    	else return this.tree_side[k];
    }
    
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        byte b0 = 0;

        switch (par5)
        {
            case 0:
            case 1:
                b0 = 0;
                break;
            case 2:
            case 3:
                b0 = 1;
                break;
            case 4:
            case 5:
                b0 = 2;
        }
        
        if(par9+b0 > 15)return 0;
        return par9+b0;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int par1, int par2)
    {
        return this.getBlockTextureFromSideAndMetadata(par1, par2);
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	par3List.add(new ItemStack(par1, 1, 0)); // sigillaria
        par3List.add(new ItemStack(par1, 1, 3)); // lepidodendron
        par3List.add(new ItemStack(par1, 1, 6)); // cordiate
        //par3List.add(new ItemStack(par1, 1, 9)); // tree 4
        //par3List.add(new ItemStack(par1, 1, 12)); // tree 5
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 1, par1 - par1%3);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
    	for(int i = 0; i < 3; i++){
        	tree_top[i] = par1IconRegister.registerIcon("animalia:tree_top_"+treeNames[i]);
        	tree_side[i] = par1IconRegister.registerIcon("animalia:tree_"+treeNames[i]);
        }
    }
}
