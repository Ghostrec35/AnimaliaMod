package animalia.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockLatePaleozoicPlanks extends BlockWood
{
	/** The type of tree this plank came from. */
	private String[] treeNames = 
		{
			"sigillaria",
			"lepidodendron",
			"cordaites",
			"tree_4",
			"tree_5",
		};
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray = new Icon[treeNames.length];

    public BlockLatePaleozoicPlanks(int par1)
    {
        super(par1);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));//sigillaria
        par3List.add(new ItemStack(par1, 1, 1));//lepidodendron
        par3List.add(new ItemStack(par1, 1, 2));//cordaites
        //par3List.add(new ItemStack(par1, 1, 3));//tree 4
        //par3List.add(new ItemStack(par1, 1, 4));//tree 5
    }

    @SideOnly(Side.CLIENT)
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
    	return iconArray[par2];
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int par1, int par2)
    {
        return this.getBlockTextureFromSideAndMetadata(par1, par2);
    }
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon("Animalia:wood_"+treeNames[i]);
        }
    }
}
