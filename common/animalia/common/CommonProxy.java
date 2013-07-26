package animalia.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import animalia.client.gui.GuiExtractor;
import animalia.common.machine.extractor.ContainerExtractor;
import animalia.common.machine.extractor.TileEntityExtractor;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case Constants.EXTRACTOR_GUI_ID: return new ContainerExtractor(player.inventory, (TileEntityExtractor) world.getBlockTileEntity(x, y, z));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		switch(ID)
		{
		    case Constants.EXTRACTOR_GUI_ID: return new GuiExtractor(player.inventory, (TileEntityExtractor) world.getBlockTileEntity(x, y, z)); 
		}
		return null;
	}

	public void registerRenders()
	{

	}

	public void registerTextureInfo()
	{

	}
        //One should not use static methods in a proxy.
	public void registerTileEntities()
	{
		TileEntity.addMapping(TileEntityExtractor.class, "extractor");
	}
}
