package animalia.client.renders;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import animalia.client.model.ModelExtractor;
import animalia.common.ref.Resources;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemExtractorRenderer implements IItemRenderer {

    private ModelExtractor modelExtractor;
    private ResourceLocation resource;
    
    public ItemExtractorRenderer(ResourceLocation resourceLocation) {
        modelExtractor = new ModelExtractor();
        resource = resourceLocation;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        switch (type) {
            case ENTITY: {
                renderGrinder(0.0F, 1.0F, 0.0F, 1.0D);
                break;
            }
            case EQUIPPED: {
                renderGrinder(0.5F, 1.5F, 0.5F, 1.0D);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
            	renderGrinder(0.5F, 1.5F, 0.5F, 1.0D);
                break;
            }
            case INVENTORY: {
                renderGrinder(0.0F, 0.80F, 0.0F, 0.85D);
                break;
            }
            default:
                break;
        }
    }

    private void renderGrinder(float x, float y, float z, double size) {
        FMLClientHandler.instance().getClient().func_110434_K().func_110577_a(resource);
        GL11.glPushMatrix(); //Start Rendering
        GL11.glTranslatef(x, y, z); //Position
        GL11.glRotatef(180F, 1F, 0, 0);
        GL11.glRotatef(-90F, 0, 1F, 0);
        GL11.glScaled(size, size, size); //Changes the size (Only really used when reading in the inventory)
        modelExtractor.renderAll(0.0625F); //Render the Extractor
        GL11.glPopMatrix(); //End Rendering
    }
}
