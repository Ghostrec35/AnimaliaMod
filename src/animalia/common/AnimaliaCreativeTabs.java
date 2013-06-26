package animalia.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AnimaliaCreativeTabs extends CreativeTabs 
{
	private int itemID = 1;
	
	public AnimaliaCreativeTabs(int par1, String par2Str) 
	{
		super(par1, par2Str);
	}
	
	public AnimaliaCreativeTabs setIcon(int iconItemID)
	{
		if(iconItemID > 0 && iconItemID < Item.itemsList.length)
			this.itemID = iconItemID;
		
		return this;
	}

	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
	    return Item.itemsList[this.itemID];
	}
}
