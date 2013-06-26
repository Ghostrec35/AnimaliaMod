package animalia.client;

import java.io.File;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import animalia.client.gui.GuiExtractor;
import animalia.client.renders.BlockExtractorInventoryRender;
import animalia.client.renders.TileEntityExtractorRenderer;
import animalia.common.Animalia;
import animalia.common.CommonProxy;
import animalia.common.Constants;
import animalia.common.machine.extractor.BlockExtractor;
import animalia.common.machine.extractor.TileEntityExtractor;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	public Animalia modInstance = Animalia.instance;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null)
		{
			switch(ID)
			{
			case Constants.EXTRACTOR_GUI_ID: return new GuiExtractor(player.inventory, (TileEntityExtractor)te); 
			}
		}
		return null;
	}

	public void registerRenders() 
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtractor.class, new TileEntityExtractorRenderer());
                BlockExtractor.modelID = RenderingRegistry.getNextAvailableRenderId();
                RenderingRegistry.registerBlockHandler(BlockExtractor.modelID, new BlockExtractorInventoryRender());
                
	}
	
	public void registerTextureInfo() 
	{
//		File file = new File("test.txt");
//		System.out.println(file.getAbsolutePath());
//		File file1 = new File("mods/animalia/textures/machines/extractor.png");
//		System.out.println("File Exists: " + file1.exists());
	}
	
	public void registerTileEntities()
	{
            //Needed a more unique name
		ClientRegistry.registerTileEntity(TileEntityExtractor.class, "AnimaliaExtractor", new TileEntityExtractorRenderer());
	}
}
