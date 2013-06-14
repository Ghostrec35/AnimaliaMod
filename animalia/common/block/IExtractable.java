package animalia.common.block;

import net.minecraft.item.ItemStack;

public interface IExtractable
{
	public ItemStack[] getExtractionPossiblities();
}
