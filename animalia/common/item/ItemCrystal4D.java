package animalia.common.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;

public class ItemCrystal4D extends Item 
{
	public ItemCrystal4D(int itemID) 
	{
		super(itemID);
	}
	
	public EnumRarity getEnumRarity()
	{
		return EnumRarity.epic;
	}
	
}
