package animalia.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class BlockEarlyPaleozoicFossil extends Block implements IExtractable
{
	public final String blockName;
	
	public BlockEarlyPaleozoicFossil(int blockID, Material material, String blockName) 
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
	public ItemStack[] getExtractionPossiblities() 
	{
		return new ItemStack[]
		{
				
		};
	}
}
