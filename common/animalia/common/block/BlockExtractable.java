package animalia.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class BlockExtractable extends Block implements IExtractable
{
	public final ItemStack extractableItem;
	
	public BlockExtractable(int blockID, Material material, ItemStack extractableItem)
	{
		super(blockID, material);
		this.extractableItem = extractableItem;
	}

	@Override
	public ItemStack onExtract(int meta) 
	{
		return extractableItem;
	}
}
