package animalia.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import animalia.common.Animalia;
import animalia.common.ref.Resources;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemOlivineArmor extends ItemArmor
{
	private static Icon HELMET; 
	private static Icon CHEST; 
	private static Icon LEGGING; 
	private static Icon BOOTS; 
	
	public ItemOlivineArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		setCreativeTab(Animalia.tabArmors);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.HELMET = par1IconRegister.registerIcon("animalia:" + "armors/olivine_helmet");
		this.CHEST = par1IconRegister.registerIcon("animalia:" + "armors/olivine_chestplate");
		this.LEGGING = par1IconRegister.registerIcon("animalia:" + "armors/olivine_leggings");
		this.BOOTS = par1IconRegister.registerIcon("animalia:" + "armors/olivine_boots");
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1) {
		if(itemID == Animalia.olivineHelmet.itemID) return HELMET;
		if(itemID == Animalia.olivineChestplate.itemID) return CHEST;
		if(itemID == Animalia.olivineLeggings.itemID) return LEGGING;
		if(itemID == Animalia.olivineBoots.itemID) return BOOTS;
		return null;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        if(stack.itemID == Animalia.olivineHelmet.itemID || stack.itemID == Animalia.olivineChestplate.itemID || stack.itemID == Animalia.olivineBoots.itemID)
        	return Resources.ARMOR_OLIVINE_1;
        else
        	return Resources.ARMOR_OLIVINE_2;
    }
}
