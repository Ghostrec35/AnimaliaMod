package animalia.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import animalia.common.block.IExtractable;

public class ItemExtractable extends Item implements IExtractable
{
	private final ItemStack extractableItem;
	
	public ItemExtractable(int itemID, ItemStack extractableItem)
	{
		super(itemID);
		this.extractableItem = extractableItem;
	}

    @Override
    public ItemStack onExtract(int meta)
    {
        return extractableItem;
    }
}
