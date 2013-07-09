package animalia.common.machine.extractor;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import animalia.common.Animalia;
import animalia.common.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockExtractor extends BlockContainer
{
	private final Random extractorRand = new Random();

	private final boolean isActive;

	private static boolean keepInventory = false;

        public static int modelID = 0; 

	public BlockExtractor(int blockID, boolean b)
	{
		super(blockID, Material.rock);
		this.isActive = b;
	}
	
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
	public static void updateExtractorBlockState(boolean b, World world, int xCoord, int yCoord, int zCoord)
	{
		int l = world.getBlockMetadata(xCoord, yCoord, zCoord);
		TileEntity tileentity = world.getBlockTileEntity(xCoord, yCoord, zCoord);
		keepInventory = true;

		if (b)
		{
			world.setBlock(xCoord, yCoord, zCoord, Animalia.extractorOn.blockID);
		}
		else
		{
			world.setBlock(xCoord, yCoord, zCoord, Animalia.extractorOff.blockID);
		}

		keepInventory = false;
		world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, l, 2);

		if (tileentity != null)
		{
			tileentity.validate();
			world.setBlockTileEntity(xCoord, yCoord, zCoord, tileentity);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityExtractor();
	}

	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}

	private void setDefaultDirection(World world, int x, int y, int z)
	{
		if (!world.isRemote)
		{
			int blockIDBellow = world.getBlockId(x, y, z - 1);
			int i1 = world.getBlockId(x, y, z + 1);
			int j1 = world.getBlockId(x - 1, y, z);
			int k1 = world.getBlockId(x + 1, y, z);
			byte b0 = 3;

			if (Block.opaqueCubeLookup[blockIDBellow] && !Block.opaqueCubeLookup[i1])
			{
				b0 = 3;
			}

			if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[blockIDBellow])
			{
				b0 = 2;
			}

			if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
			{
				b0 = 5;
			}

			if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
			{
				b0 = 4;
			}

			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}

	// Make sure you Annotate this as Client Side Only
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
	{
		return blockIcon;
	}

	// Make sure you Annotate this as Client Side Only
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
                //this will only be used for particle generation
		this.blockIcon = iconRegister.registerIcon("animalia:machines/extractor_particles");
	}
        
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		TileEntity te = world.getBlockTileEntity(x, y, z);

		if (te == null /*|| !(te instanceof TileEntityExtractor)*/)
		{
			return false;
		}

		player.openGui(Animalia.instance, Constants.EXTRACTOR_GUI_ID, world, x, y, z);
		return true;
	}

	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
	{
		int l = MathHelper.floor_double((double) (par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
		}

		if (l == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
		}

		if (l == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
		}

		if (l == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
		}

		if (par6ItemStack.hasDisplayName())
		{
			((TileEntityExtractor) par1World.getBlockTileEntity(par2, par3, par4)).setCustomName(par6ItemStack.getDisplayName());
		}
	}

	public int idDropped(int par1, Random par2Random, int par3)
	{
		return Block.furnaceIdle.blockID;
	}

	public int idPicked(World world, int i, int j, int k)
	{
		return Animalia.extractorOff.blockID;
	}
        
        @SideOnly(Side.CLIENT)
        @Override
        public int getRenderType() {
            return modelID; //To change body of generated methods, choose Tools | Templates.
        }
        
        
}
