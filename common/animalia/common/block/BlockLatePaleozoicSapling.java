package animalia.common.block;

import java.util.List;
import java.util.Random;

import animalia.common.world.gen.feature.WorldGenTreeCordiate;
import animalia.common.world.gen.feature.WorldGenTreeLepidodendron;
import animalia.common.world.gen.feature.WorldGenTreeSigillaria;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockLatePaleozoicSapling extends BlockFlower{

	
	private String[] treeNames = 
		{
			"sigillaria",
			"lepidodendron",
			"cordaites",
			"tree_4",
			"tree_5",
			};
	
	private Icon[] iconArray = new Icon[treeNames.length];
	
	
	/**
	 * constructor - par1 is block id.
	 */
    public BlockLatePaleozoicSapling(int par1)
    {	
        super(par1, Material.plants);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        //this.setCreativeTab(CreativeTabs.tabDecorations);
        
        //System.out.println("Highlands Saplings texture file: " + this.currentTexture);
    }
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
    	if(par2 < 3)return iconArray[0];
    	if(par2 < 6)return iconArray[1];
    	if(par2 < 9)return iconArray[2];
    	if(par2 < 12)return iconArray[3];
    	return iconArray[4];
    	
    }
    
    public Icon getIcon(int par1, int par2)
    {
        return this.getBlockTextureFromSideAndMetadata(par1, par2);
    }
    
    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
            {
            	if(par1World.getBlockMetadata(par2, par3, par4)%3 < 2)
            		par1World.setBlock(par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4)+1, 2);
            	else{
            		growTree(par1World, par2, par3, par4, par5Random);
            	}
            }
        }
    }
    
    
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	//System.out.println("Sapling activated!  " + (player.inventory.getCurrentItem().getItemDamage()==15) + "  " + (player.inventory.getCurrentItem().itemID==Item.dyePowder.itemID));
    	
    	
	    if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItemDamage()==15 && player.inventory.getCurrentItem().itemID==Item.dyePowder.itemID)
	    {
	    	if(player.capabilities.isCreativeMode == true)
	    		growTree(par1World,par2,par3,par4,new Random());
	    	else{
	    	//increase metadata by one if it is not ready to grow yet
	    	if(par1World.getBlockMetadata(par2, par3, par4)%3 < 2){
	    		par1World.setBlock(par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4)+1, 2);
                if (!par1World.isRemote)
                {
                    par1World.playAuxSFX(2005, par2, par3, par4, 0);
                }
	    	}
	    	else growTree(par1World,par2,par3,par4,new Random());
	    	
	    	//reduce bonemeal stack size by one
	    	player.inventory.getCurrentItem().stackSize--;
	    	}
	    }
	    return true;
    }
    
    public void growTree(World par1World, int i, int j, int k, Random r){
    	int meta = par1World.getBlockMetadata(i, j, k);
    	par1World.setBlock(i, j, k, 0, 0, 2);
    	boolean isTreeGrowSuccess = false;
    	
    	if(meta / 3 == 0)isTreeGrowSuccess = new WorldGenTreeSigillaria(10, 8, true).generate(par1World, r, i, j, k);
    	else if(meta / 3 == 1)isTreeGrowSuccess = new WorldGenTreeLepidodendron(18, 10, true).generate(par1World, r, i, j, k);
    	else if(meta / 3 == 2)isTreeGrowSuccess = new WorldGenTreeCordiate(30, 10, true).generate(par1World, r, i, j, k);
    	
    	if(!isTreeGrowSuccess)par1World.setBlock(i, j, k, this.blockID, meta, 2);
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
    
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 1;
    }
    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    
    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
    	//if(treeType == 12) return EnumPlantType.Water;
        return EnumPlantType.Plains;
    }

    @Override
    public int getPlantID(World world, int x, int y, int z)
    {
        return blockID;
    }
    @Override
    public int getPlantMetadata(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
    
    public int damageDropped(int par1)
    {
        return par1 - par1%3;
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	for(int i = 0; i < treeNames.length; i++){
    		this.iconArray[i] = par1IconRegister.registerIcon("animalia:sapling_"+treeNames[i]);
    	}
    }
}












