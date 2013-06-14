package animalia.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import animalia.common.block.IExtractable;

public class ItemExtractable extends Item implements IExtractable
{
	private final ItemStack[] extractableItems;
	
	public ItemExtractable(int itemID, ItemStack[] extractableItems)
	{
		super(itemID);
		this.extractableItems = extractableItems;
	}

	@Override
	public ItemStack[] getExtractionPossiblities() 
	{
		return extractableItems;
	}
}
