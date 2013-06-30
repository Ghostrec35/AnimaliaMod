package animalia.common.world.gen.feature;

import java.util.Random;

import animalia.common.Animalia;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeLepidodendron extends WorldGenerator
{
	
	private int woodID;
	private int leavesID;
    private int woodMeta;
    private int leavesMeta;
    
    private int minHeight;
    private int maxHeight;
    
    private boolean notifyFlag;
    
    private World world;
    private Random random;

    /** Constructor - gets the generator for the correct highlands tree
     * @param lmd leaf meta data
     * @param wmd wood meta data
     * @param wb wood block id
     * @param lb leaf block id
     * @param minH minimum height of tree trunk
     * @param maxH max possible height above minH the tree trunk could grow
     * @param notify whether or not to notify blocks of the tree being grown.
     *  Generally false for world generation, true for saplings.
     */
    public WorldGenTreeLepidodendron(int minH, int maxH, boolean notify)
    {
        minHeight = minH;
        maxHeight = maxH;
        woodID = Animalia.logLP.blockID;
        leavesID = Animalia.leavesLP.blockID;
        woodMeta = 3;
        leavesMeta = 1;
        
        notifyFlag = notify;
    }

    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;
    	

    	//finds top block for the given x,z position (excluding leaves and grass)
        for (boolean var6 = false; (world.getBlockId(locX, locY, locZ) == 0 || Block.blocksList[world.getBlockId(locX, locY, locZ)].isLeaves(world, locX, locY, locZ) && locY > 0); --locY);
        //locY is now the highest solid terrain block
        
        if(!(world.getBlockId(locX, locY, locZ) == Block.grass.blockID || world.getBlockId(locX, locY, locZ) == Block.dirt.blockID))return false;
        if(!isCubeClear(locX, locY+2, locZ, 1, 15))return false;
        
    	//generates the trunk
    	locY++;
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	
    	//Generate trunk
		for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
    	}
		//Generate leaves
		int h = locY + treeHeight - 3;
		generateLeafLayerCircle(world, random, 1, locX, locZ, h);
		generateLeafLayerCircle(world, random, 3.5, locX, locZ, h+1);
		generateLeafLayerCircle(world, random, 2.5, locX, locZ, h+2);
		generateLeafLayerCircle(world, random, 1.5, locX, locZ, h+3);
		setBlockInWorld(locX, h+4, locZ, this.leavesID, this.leavesMeta);
    	
    	return true;
    }
    
    /*
     * tree helper methods - if you want to use these in other tree classes, or create a tree base class that defines these methods, go ahead
     */
    
    //generates a circle of leaves centered around xo, h, zo with radius r.
    private void generateLeafLayerCircle(World world, Random random, double radius, int xo, int zo, int h){
    	for(int x = (int)Math.ceil(xo - radius); x <= (int)Math.ceil(xo + radius); x++){
			for(int z = (int)Math.ceil(zo - radius); z <= (int)Math.ceil(zo + radius); z++){
				double xfr = z - zo;
				double zfr = x- xo;
				
				if(xfr * xfr + zfr * zfr <= radius * radius){
					setBlockInWorld(x, h, z, this.leavesID, this.leavesMeta);
				}
			}
		}
    }
    
    //sets a block to wood or leaves, overwriting the block if it's allowed
    //leaves overwrite air, wood overwrites air, leaves, water.
    private void setBlockInWorld(int x, int y, int z, int id, int meta){
    	try{
			if(id == this.woodID && (world.isAirBlock(x,y,z) || Block.blocksList[world.getBlockId(x, y, z)].isLeaves(world, x, y, z)
					|| world.getBlockId(x,y,z) == Block.waterStill.blockID || world.getBlockId(x,y,z) == Block.waterMoving.blockID)){
				if(notifyFlag) world.setBlock(x, y, z, id, meta, 3);
		    	else world.setBlock(x, y, z, id, meta, 2);
			}
			else if(id == this.leavesID && world.isAirBlock(x,y,z)){
				if(notifyFlag) world.setBlock(x, y, z, id, meta, 3);
		    	else world.setBlock(x, y, z, id, meta, 2);
			}
    	}
    	catch(RuntimeException e){
    		if(e.getMessage().equals("Already decorating!!")){
    			System.out.println("Error: Tree block couldn't generate!");
    		}
    		//e.printStackTrace();
    	}
    }
    
    //checks a cube of space to see if it's clear of obstructions (any block besides leaves)
    //the point x, y, z is the center of the bottom face of the cube.  The bottom face is a square with side length 2*(radius) + 1.
    private boolean isCubeClear (int x, int y, int z, int radius, int height){
    	for(int i = x-radius; i <= x+radius; i++){
    		for(int j = z-radius; j <= z+radius; j++){
    			for(int k = y; k <= y+height; k++){
    				if(!(world.getBlockId(i, k, j) == 0 || Block.blocksList[world.getBlockId(i, k, j)].isLeaves(world, i, k, j)))return false;
    			}
    		}
    	}
    	return true;
    }
}













