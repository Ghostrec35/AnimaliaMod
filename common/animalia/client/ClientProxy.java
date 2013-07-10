package animalia.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import animalia.client.gui.GuiExtractor;
import animalia.client.renders.ItemExtractorRenderer;
import animalia.client.renders.RenderExtractor;
import animalia.common.Animalia;
import animalia.common.CommonProxy;
import animalia.common.Constants;
import animalia.common.machine.extractor.BlockExtractor;
import animalia.common.machine.extractor.TileEntityExtractor;
import animalia.common.ref.Resources;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	public Animalia modInstance = Animalia.instance;

	public void registerRenders() 
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtractor.class, new RenderExtractor());
		MinecraftForgeClient.registerItemRenderer(Animalia.extractorOff.blockID, new ItemExtractorRenderer(Resources.EXTRACTOR_OFF));  
		MinecraftForgeClient.registerItemRenderer(Animalia.extractorOn.blockID, new ItemExtractorRenderer(Resources.EXTRACTOR_ON));   
	}
	
	public void registerTextureInfo() 
	{
	    
	}
	
	public void registerTileEntities()
	{
		ClientRegistry.registerTileEntity(TileEntityExtractor.class, "AnimaliaExtractor", new RenderExtractor());
	}
}
