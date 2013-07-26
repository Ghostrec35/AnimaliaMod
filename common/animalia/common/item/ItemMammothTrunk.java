package animalia.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemMammothTrunk extends ItemFood
{
    public ItemMammothTrunk(int par1, int par2, boolean par3)
    {
        super(par1, par2, par3);
    }
    
    protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote)
        {
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, 20 * 3, 1));
        }
    }
}
