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
import net.minecraft.client.Minecraft;

public class TileEntityExtractorRenderer extends TileEntitySpecialRenderer {

    ModelExtractor model;

    public TileEntityExtractorRenderer() {
        model = new ModelExtractor();
    }

    public void renderAModelAt(TileEntityExtractor tileEntity, double d, double d1, double d2, float f) {
        float rotation = 0.0F;
        if (tileEntity.worldObj != null) {
            rotation = tileEntity.getBlockMetadata();
        }

        if (rotation == 2) {
            rotation = 180.0F;
        } else if (rotation == 3) {
            rotation = 0.0F;
        } else if (rotation == 4) {
            rotation = 270.0F;
        } else if (rotation == 5) {
            rotation = 90.0F;
        }
        if(tileEntity.isRunning()){
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/animalia/textures/machines/extractor_on.png");
        }else{
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/animalia/textures/machines/extractor_off.png");
        }
        
        GL11.glPushMatrix();
        
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.15F, (float) d2 + 0.5F);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(0.830F, -0.75F, -0.830F);
        
        model.renderAll(0.0625F);
        
        GL11.glPopMatrix();
        Minecraft.getMinecraft().renderEngine.resetBoundTexture();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double d0, double d1, double d2, float f) {
        this.renderAModelAt((TileEntityExtractor) tileEntity, d0, d1, d2, f);
    }
}
