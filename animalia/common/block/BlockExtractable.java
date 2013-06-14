package animalia.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class BlockExtractable extends Block implements IExtractable
{
	public final ItemStack[] extractableItems;
	
	public BlockExtractable(int blockID, Material material, ItemStack[] extractableItems)
	{
		super(blockID, material);
		this.extractableItems = extractableItems;
	}

	@Override
	public ItemStack[] getExtractionPossiblities() 
	{
		return extractableItems;
	}
}
