package animalia.common.item;

import animalia.common.Animalia;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemOlivineArmor extends ItemArmor
{
	public ItemOlivineArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        if(stack.itemID == Animalia.olivineHelmet.itemID || stack.itemID == Animalia.olivineChestplate.itemID || stack.itemID == Animalia.olivineBoots.itemID)
        	return "/mods/animalia/textures/items/armor_renders/olivine_1.png";
        else
        	return "/mods/animalia/textures/items/armor_renders/olivine_2.png";
    }
}
