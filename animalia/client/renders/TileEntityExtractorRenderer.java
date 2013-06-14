package animalia.client.renders;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import animalia.client.model.ModelExtractor;
import animalia.common.machine.extractor.TileEntityExtractor;
import cpw.mods.fml.client.FMLClientHandler;

public class TileEntityExtractorRenderer extends TileEntitySpecialRenderer
{
	ModelExtractor model;
	
	public TileEntityExtractorRenderer()
	{
		model = new ModelExtractor();
	}
	
	public void renderAModelAt(TileEntityExtractor tileEntity, double d, double d1, double d2, float f) 
	{
		int rotation = 0;
		if(tileEntity.worldObj != null)
		{
			rotation = tileEntity.getBlockMetadata();
		}
		
		Block block = tileEntity.getBlockType();
		World world = tileEntity.worldObj;
		Tessellator tessellator = Tessellator.instance;
        float brightness = block.getBlockBrightness(world, (int)d, (int)d1, (int)d2);
        int l = world.getLightBrightnessForSkyBlocks((int)d, (int)d1, (int)d2, 0);
        int l1 = l % 65536;
        int l2 = l / 65536;
        tessellator.setColorOpaque_F(brightness, brightness, brightness);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)l1, (float)l2); 
		
		if(rotation == 2)
			rotation = 180;
		else if(rotation == 3)
			rotation = 0;
		else if(rotation == 4)
			rotation = 90;
		else if(rotation == 5)
			rotation = 270;
	
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/animalia/textures/machines/extractor.png");
		model.renderTileEntity();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double d0, double d1, double d2, float f) 
	{
		this.renderAModelAt((TileEntityExtractor)tileEntity, d0, d1, d2, f);
	}
}
