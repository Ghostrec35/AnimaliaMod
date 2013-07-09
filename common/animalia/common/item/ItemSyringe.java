package animalia.common.item;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import animalia.common.Animalia;
import animalia.common.block.IExtractable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSyringe extends Item implements IExtractable
{
    private String[] syringeNames = 
        {
            "empty",
            "early_paleo_dna",
            "late_paleo_dna",
            "meso_dna",
            "fossil_5",
        };
    
    @SideOnly(Side.CLIENT)
    private Icon[] textures = new Icon[2];
    
	public ItemSyringe(int itemID)
	{
		super(itemID);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(Animalia.tabMaterial);
	}
	
	public Icon getIconFromDamage(int par1)
    {
	    if(par1 == 0)
	        return textures[0];
	    else
	        return textures[1];
    }
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return Animalia.saplingLP.blockID;
    }

	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List list)
    {
	    list.add(new ItemStack(par1, 1, 0));
	    list.add(new ItemStack(par1, 1, 1));
	    list.add(new ItemStack(par1, 1, 2));
    }
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register)
	{
	    textures[0] = register.registerIcon("animalia:syringe_empty");
	    textures[1] = register.registerIcon("animalia:syringe_full");
	}
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + syringeNames[i];
    }
	
	public void addInformation(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4) 
	{
	    if(itemstack.getItemDamage() == 0)
	        list.add("An Early Paleozoic Fossil");
	    else if(itemstack.getItemDamage() == 1)
	        list.add("A Late Paleozoic Fossil");
	    else if(itemstack.getItemDamage() == 2)
	        list.add("A Mesozoic Fossil");
	    else
	        list.add("Invalid Value");
	}

    @Override
    public ItemStack onExtract(int meta)
    {
        Random rand = new Random();
        if(meta == 0)
        {
            if(rand.nextInt(20) < 3)
                return new ItemStack(Animalia.fossilItem, 1, meta);
        }
        else if(meta == 1)
        {
            if(rand.nextInt(5) < 1)
                return new ItemStack(Animalia.fossilItem, 1, meta);
        }
        else if(meta == 2)
        {
            if(rand.nextInt(10) < 3)
                return new ItemStack(Animalia.fossilItem, 1, meta);
        }
        return null;
    }
}
