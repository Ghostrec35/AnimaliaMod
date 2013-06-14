package animalia.common.machine.extractor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import animalia.common.MachineInfo;
import animalia.common.network.IHandlePacket;
import animalia.common.network.PacketHandler;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class TileEntityExtractor extends TileEntity implements ISidedInventory, IHandlePacket
{
	/*
	 * I'm honestly not sure at the moment what these are used for.Once I get some time to look
	 * around in what getSizeInventorySide()is used for, i'll rename these accordingly.
	 * 
	 * Answer: These are the arrays containing slots that can be accessed per sideFrom DarkGuardsman
	 */
	private static final int[] input = new int[] { 0 };
	private static final int[] side = new int[] { 2, 1 };
	private static final int[] fuel = new int[] { 1 };

	public ItemStack[] containedItems = new ItemStack[3];

	/**
	 * The number of ticks the Extractor will keep extracting
	 **/
	public int extractorRunTime, totalItemRunTime, currentItemRunTime = 0;

	/**
	 * Localized String. Does not need to be Initialized, but will be used as the Inventory Name if
	 * it is.
	 **/
	public String customString;

	@Override
	public int getSizeInventory()
	{
		return this.containedItems.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.containedItems[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		if (this.containedItems[slot] != null)
		{
			ItemStack itemstack;

			if (this.containedItems[slot].stackSize <= amount)
			{
				itemstack = this.containedItems[slot];
				this.containedItems[slot] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.containedItems[slot].splitStack(amount);

				if (this.containedItems[slot].stackSize == 0)
				{
					this.containedItems[slot] = null;
				}

				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if (this.containedItems[slot] != null)
		{
			ItemStack itemstack = this.containedItems[slot];
			this.containedItems[slot] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
	{
		this.containedItems[slot] = itemstack;

		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
		{
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName()
	{
		return this.isInvNameLocalized() ? this.customString : "container.furnace";
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items");
		this.containedItems = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.containedItems.length)
			{
				this.containedItems[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.extractorRunTime = nbt.getShort("RunTime");
		this.totalItemRunTime = nbt.getShort("ItemRunTime");
		this.currentItemRunTime = getItemRunTime(this.containedItems[1]);

		if (nbt.hasKey("CustomName"))
		{
			this.customString = nbt.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setShort("RunTime", (short) this.extractorRunTime);
		nbt.setShort("ItemRunTime", (short) this.currentItemRunTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.containedItems.length; ++i)
		{
			if (this.containedItems[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.containedItems[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);

		if (this.isInvNameLocalized())
		{
			nbt.setString("CustomName", this.customString);
		}
	}

	public static int getItemRunTime(ItemStack itemstack)
	{
		if (!(itemstack == null))
			return MachineInfo.getExtractorFuelRuntime(itemstack);
		else
			return 0;
	}

	@Override
	public void updateEntity()
	{
		boolean isRunning = this.extractorRunTime > 0;
		boolean flag1 = false;

		if (this.extractorRunTime > 0)
		{
			--this.extractorRunTime;
		}

		if (!this.worldObj.isRemote)
		{
			if (this.extractorRunTime == 0 && this.canExtract())
			{
				this.totalItemRunTime = this.extractorRunTime = getItemRunTime(this.containedItems[1]);

				if (this.extractorRunTime > 0)
				{
					flag1 = true;

					if (this.containedItems[1] != null)
					{
						--this.containedItems[1].stackSize;

						if (this.containedItems[1].stackSize == 0)
						{
							this.containedItems[1] = this.containedItems[1].getItem().getContainerItemStack(containedItems[1]);
						}
					}
				}
			}

			if (this.isRunning() && this.canExtract())
			{
				if (++this.currentItemRunTime == 200)
				{
					this.currentItemRunTime = 0;
					this.extractItem();
					flag1 = true;
				}
			}
			else
			{
				this.currentItemRunTime = 0;
			}

			if (isRunning != this.extractorRunTime > 0)
			{
				flag1 = true;
				BlockExtractor.updateExtractorBlockState(this.extractorRunTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (flag1)
		{
			this.onInventoryChanged();
		}

		PacketHandler.sendTileEntityPacket(this, "Animalia", this.currentItemRunTime, this.extractorRunTime);
	}

	public boolean canExtract()
	{
		return false;
	}

	public void extractItem()
	{

	}

	public boolean isRunning()
	{
		return this.extractorRunTime > 0;
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

	@Override
	public void handlePacketData(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player)
	{
		ByteArrayDataInput bads = ByteStreams.newDataInput(packet.data);

		this.currentItemRunTime = bads.readInt();
		this.extractorRunTime = bads.readInt();
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return this.customString != null && this.customString.length() > 0;
	}

	@Override
	public boolean isStackValidForSlot(int slot, ItemStack itemstack)
	{
		return slot == 2 ? false : (slot == 1 ? isItemFuel(itemstack) : true);
	}

	public static boolean isItemFuel(ItemStack itemstack)
	{
		return getItemRunTime(itemstack) > 0;
	}

	public int getTimeRemainingScaled(int i)
	{
		if (this.totalItemRunTime == 0)
		{
			this.totalItemRunTime = 200;
		}
		return this.currentItemRunTime * i / this.totalItemRunTime;
	}

	public int getProgressScaled(int i)
	{
		return this.extractorRunTime * i / 200;
	}

	public void setCustomName(String string)
	{
		this.customString = string;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1)
	{
		return var1 == 0 ? side : (var1 == 1 ? input : fuel);
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j)
	{
		return this.isStackValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j)
	{
		return j != 0 || i != 1 || itemstack.itemID == Item.bucketEmpty.itemID;
	}
}
