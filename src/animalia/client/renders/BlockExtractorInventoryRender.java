/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animalia.client.renders;

import animalia.common.machine.extractor.BlockExtractor;
import animalia.common.machine.extractor.TileEntityExtractor;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

/**
 *
 * @author Home
 */
public class BlockExtractorInventoryRender implements ISimpleBlockRenderingHandler{
    private static RenderExtractor teRenderer = new RenderExtractor();
    private static TileEntityExtractor dummyExtractor = new TileEntityExtractor();
    
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        teRenderer.renderTileEntityAt(dummyExtractor, 0, 0, 0, 0.0625F);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        //Do No world rednering, the tile entity does this
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory() {
        return true;
    }

    @Override
    public int getRenderId() {
        return BlockExtractor.modelID;
    }
    
}
