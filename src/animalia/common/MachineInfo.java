package animalia.common;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import animalia.api.AnimaliaAccess;

public class MachineInfo 
{
	public static int getExtractorFuelRuntime(ItemStack itemstack)
	{
		if(itemstack.itemID == Item.coal.itemID)
			return 400;
		else
			return AnimaliaAccess.getExternalFuelRuntime(itemstack);
	}
}
