package animalia.common.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import animalia.common.Animalia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFossil extends Block
{
    private String[] fossilNames =
        {
            "early_paleo",
            "late_paleo",
            "meso",
            "fossil_4",
            "fossil_5",
        };
    
    @SideOnly(Side.CLIENT)
    private Icon[] textures = new Icon[fossilNames.length];
    
    public BlockFossil(int par1, Material par2Material)
    {
        super(par1, par2Material);
        setCreativeTab(Animalia.tabBlock);
    }
    
    @Override
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 1, par1);
    }
    
    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }
    
    @Override
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
    
    @Override
    public Icon getIcon(int par1, int par2)
    {
        return textures[par2];
    }
    
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0)); // Early Paleozoic Fossil
        par3List.add(new ItemStack(par1, 1, 1)); // Late Paleozoic Fossil
        par3List.add(new ItemStack(par1, 1, 2)); // Mesozoic Fossil
        //par3List.add(new ItemStack(par1, 1, 3)); // fossil 4
        //par3List.add(new ItemStack(par1, 1, 4)); // fossil 5
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return 0;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        for(int i = 0; i < 3; i++)
        {
            textures[i] = par1IconRegister.registerIcon("animalia:fossil_" + fossilNames[i]);
        }
    }
    
    @Override
    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        int metadataVal = world.getBlockMetadata(x, y, z);
        world.setBlock(x, y, z, 0);
        if (!player.capabilities.isCreativeMode && player.getHeldItem() != null && !world.isRemote) {
            if (player.getHeldItem().getItem() instanceof ItemPickaxe) {
                if (world.rand.nextFloat() < 0.5F) {
                    EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Block.cobblestone));
                    item.motionX = world.rand.nextDouble() - 0.5D;
                    item.motionY = world.rand.nextDouble() - 0.5D;
                    item.motionZ = world.rand.nextDouble() - 0.5D;
                    world.spawnEntityInWorld(item);
                } else {
                    EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Item.bone));
                    item.motionX = world.rand.nextDouble() - 0.5D;
                    item.motionY = world.rand.nextDouble() - 0.5D;
                    item.motionZ = world.rand.nextDouble() - 0.5D;
                    world.spawnEntityInWorld(item);
                }
            } else if (player.getHeldItem().itemID == Animalia.chiselItem.itemID) {
                EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Animalia.fossilItem.itemID, 1, metadataVal));
                item.motionX = 0;
                item.motionZ = 0;
                item.motionY += 0.001;
                world.spawnEntityInWorld(item);
            }
        }
        return true;
    }
}
