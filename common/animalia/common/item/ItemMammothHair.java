package animalia.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import animalia.common.Animalia;
import animalia.common.block.IExtractable;

public class ItemMammothHair extends Item implements IExtractable
{
    public ItemMammothHair(int par1)
    {
        super(par1);
    }

    @Override
    public ItemStack onExtract(int meta)
    {
        //Metadata value for the syringe will be different later.
        return new ItemStack(Animalia.syringeItem, 1, 0);
    }
}
