package animalia.common.block;

import animalia.common.Animalia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import net.minecraftforge.common.IShearable;

public class BlockLatePaleozoicLeaves extends BlockLeaves implements IShearable
{
	private String[] treeNames = 
		{
			"sigillaria",
			"lepidodendron",
			"cordaites",
			"tree_4",
			"tree_5",
		};
    
    private Icon[] textureFast;
    private Icon[] textureFancy;
    
    int[] adjacentTreeBlocks;

    public BlockLatePaleozoicLeaves(int id)
    {
        super(id);
        //this.setCreativeTab(CreativeTabs.tabBlock);
        textureFast = new Icon[5];
        textureFancy = new Icon[5];
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(20) == 0 ? 1 : 0;//chance to drop sapling. Change for different trees
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Animalia.saplingLP.blockID;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
    	if (!par1World.isRemote)
        {
            ArrayList<ItemStack> items = getBlockDropped(par1World, par2, par3, par4, par5, par7);

            for (ItemStack item : items)
            {
                if (par1World.rand.nextFloat() <= par6)
                {
                    this.dropBlockAsItem_do(par1World, par2, par3, par4, item);
                }
            }
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
    	this.graphicsLevel = Block.leaves.graphicsLevel;
    	
    	if(this.graphicsLevel)return textureFancy[par2];
    	else return textureFast[par2];
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
    	par2 = par2 % 8;
    	this.graphicsLevel = Block.leaves.graphicsLevel;
    	
    	if(this.graphicsLevel)return textureFancy[par2];
    	else return textureFast[par2];
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta.  Will return right-side-up logs of each tree type.
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0)); // sigillaria
        par3List.add(new ItemStack(par1, 1, 1)); // lepidodendron
        par3List.add(new ItemStack(par1, 1, 2)); // cordiates
        //par3List.add(new ItemStack(par1, 1, 3)); // tree 4
        //par3List.add(new ItemStack(par1, 1, 4)); // tree 5
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 1, par1);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        for(int i = 0; i < 5; i++){
        	textureFancy[i] = par1IconRegister.registerIcon("Animalia:leaves_"+treeNames[i]);
        	textureFast[i] = par1IconRegister.registerIcon("Animalia:leaves_"+treeNames[i]+"_opaque");
        }
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, 0));
        return ret;
    }
}
