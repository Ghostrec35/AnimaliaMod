package animalia.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IExtractorResultHandler 
{
	public void onExtraction(EntityPlayer entityPlayer, ItemStack itemstack);
}
