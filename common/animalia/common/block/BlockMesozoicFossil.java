package animalia.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class BlockMesozoicFossil extends Block implements IExtractable
{
	public final String blockName;

	public BlockMesozoicFossil(int blockID, Material material, String blockName)
	{
		super(blockID, material);
		this.blockName = blockName;
	}

	@Override
	public String getUnlocalizedName()
	{
		return "tile." + blockName;
	}

	@Override
	public ItemStack[] getExtractionPossiblities(int meta)
	{
		return new ItemStack[] {

		};
	}
}
