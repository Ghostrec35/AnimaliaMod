package animalia.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

import animalia.client.model.ModelExtractor;
import animalia.common.machine.extractor.TileEntityExtractor;
import animalia.common.ref.Resources;
import net.minecraft.client.Minecraft;

public class RenderExtractor extends TileEntitySpecialRenderer {

    ModelExtractor model;

    public RenderExtractor() {
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
        	func_110628_a(Resources.EXTRACTOR_ON);
        }else{
        	func_110628_a(Resources.EXTRACTOR_OFF);
        }
        
        GL11.glPushMatrix();
        
        //To change block position
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.05F, (float) d2 + 0.5F);
        //To change block rotation
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        //To change block size
        GL11.glScalef(0.830F, -0.70F, -0.830F);
        
        model.renderAll(0.0625F);
        
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double d0, double d1, double d2, float f) {
        this.renderAModelAt((TileEntityExtractor) tileEntity, d0, d1, d2, f);
    }
}
